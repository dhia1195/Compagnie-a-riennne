/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.quicklines.utils;

/**
 *
 * @author houce
 */
import java.sql.*;
import java.sql.Connection;
import java.lang.*;
import java.lang.Object;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author houce
 * @param <T>
 */



public class MyMapper <T> {
    private Connection cnx;

    public MyMapper(){
        cnx = MaConnexion.getInstance().getCnx();
    }
    public ResultSetMetaData rsmd(Entity e,String tableName) throws SQLException {
        String req="SELECT * FROM `"+tableName+"` LIMIT 0";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs;
        rs = ps.executeQuery(req);
        ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
        rs.close();
        return rsmd;
    }
    public void Insert(Entity e,String tableName)throws SQLException {
        ResultSetMetaData rsmd = rsmd(e,tableName);
        String reqPart1="INSERT INTO `"+tableName +"`";
        String reqPart2="(";
        for (int i=2;i<rsmd.getColumnCount() ;i++){
            reqPart2=reqPart2+"`"+rsmd.getColumnName(i)+"`,";
        }
        reqPart2=reqPart2+"`"+rsmd.getColumnName(rsmd.getColumnCount())+"`) ";
        String reqPart3=" VALUES(";
        for (int i=2;i<rsmd.getColumnCount() ;i++){
            reqPart3=reqPart3+"?,";
        }
        reqPart3=reqPart3+"?);";
        String req=reqPart1+reqPart2+reqPart3;
        //System.out.println(req);
        PreparedStatement ps = cnx.prepareStatement(reqPart1+reqPart2+reqPart3);
        for(int i=1; i<=((rsmd.getColumnCount())-1);i++){
            try{
                ps.setObject(i,e.getValues().get(i));
            } catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        ps.executeUpdate();
        ps.close();
    }
    
    public void Update(Entity e,String dataBaseName,String tableName)throws SQLException {
        ResultSetMetaData rsmd = rsmd(e,tableName);
        String reqPart1="UPDATE `"+dataBaseName+"`.`"+tableName+"` SET ";
        String reqPart2="";
        for (int i=2;i<rsmd.getColumnCount() ;i++){
            reqPart2=reqPart2+"`"+rsmd.getColumnName(i)+"`=?,";
        }
        reqPart2=reqPart2+"`"+rsmd.getColumnName(rsmd.getColumnCount())+"`=? WHERE `"+rsmd.getColumnName(1)+"`="+e.getValues().get(0);
        String req=reqPart1+reqPart2;
        System.out.println(req);
        PreparedStatement ps=cnx.prepareStatement(req);
        try {
            Entity entity=e.getClass().newInstance();
            Entity entityId=e.getClass().newInstance();
            List<Object> id= new ArrayList<>(); 
            id.add(e.getValues().get(0));
            entityId.setValues(id);
            entity.setValues(findByEntity(e,tableName).getValues());
            if((findByEntity(e,tableName).getValues().equals(e.getValues()))==false){
                for(int i=1;i<rsmd.getColumnCount();i++){
                    ps.setObject(i, e.getValues().get(i));
                }
                ps.executeUpdate();
                System.out.println("Mise à jour réussie!");
            }else{
                System.out.println("Mise à jour pas nécessaire!");
            }
        } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
            Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
        ps.close();
    }
    
    public Entity findByEntity(Entity e,String tableName) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        ResultSetMetaData rsmd = rsmd(e,tableName);
        String req;
        req = "SELECT * FROM "+tableName+" WHERE ";
        for(int i=1;i<rsmd.getColumnCount();i++){
            req=req+"`"+rsmd.getColumnName(i)+"`=? AND ";
        }
        req=req+"`"+rsmd.getColumnName(rsmd.getColumnCount())+"`=?";
        PreparedStatement ps=cnx.prepareStatement(req);
        for(int i=1;i<=rsmd.getColumnCount();i++ ){
            try{
                ps.setObject(i,e.getValues().get(i-1));
            } catch(SQLException ex){
                System.out.println(ex.toString());
            }
        }
        ResultSet rs=ps.executeQuery();
        Entity entity = e.getClass().newInstance();
        ArrayList <Object> values = new ArrayList();
        if(rs.next()){
            for (int i=1;i<=rsmd.getColumnCount();i++){
                try{
                    values.add(rs.getObject(i));
                }catch(SQLException ex){
                    System.out.println(ex.toString());
                }
            }
            entity.setValues(values);
            System.out.println("FOUND!");
            ps.close();
            return entity;
        }
        System.out.println("NOT FOUND!");
        ps.close();
        return e.getClass().newInstance();
    }
    
    public Entity findById(int id,Entity e,String tableName) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException{
        ResultSetMetaData rsmd = rsmd(e,tableName);
        String req;
            req = "SELECT * FROM "+tableName+" WHERE `"+rsmd.getColumnName(1)+"`="+id;
        PreparedStatement ps=cnx.prepareStatement(req);
        ResultSet rs=ps.executeQuery();
        Entity entity = e.getClass().newInstance();
        ArrayList <Object> values = new ArrayList();
        if(rs.next()){
            for (int i=1;i<=rsmd.getColumnCount();i++){
                try{
                    values.add(rs.getObject(i));
                }catch(SQLException ex){
                    System.out.println(ex.toString());
                }
            }
            entity.setValues(values);
            System.out.println("FOUND!");
            ps.close();
            return entity;
        }
        System.out.println("NOT FOUND!");
        ps.close();
        return entity;
    }
    
    HashMap<Integer,Object> Args(Entity e, ArrayList<Integer> indexes, ArrayList<Object> values ){
        HashMap<Integer,Object> args = new HashMap();
        for (int i=0; i<indexes.size();i++){
            args.put(indexes.get(i), values.get(i));
        }
        return args;
    }
    
    public List<Entity> findByArgs(Entity e,String tableName,HashMap<Integer,Object> args) throws SQLException,InstantiationException,IllegalAccessException{
        ResultSetMetaData rsmd = rsmd(e,tableName);
        List<Entity> temp = new ArrayList<>();
        ArrayList <Object> values = new ArrayList();
        String req="SELECT * FROM "+tableName+" WHERE";
        //Map.Entry<Integer,Object> paires = args.entrySet();
        Map<Integer,Object> tmp= new HashMap<>();
        Set paires = args.entrySet();
        if(!paires.iterator().hasNext()){
            try {
                req=req+"`"+rsmd.getColumnName(args.keySet().stream().collect(Collectors.toList()).get(0))+"`=?" ;
            } catch (SQLException ex) {
                Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
        }else{
            tmp=args;
            List<Integer> indexes= new ArrayList();
            indexes=tmp.keySet().stream().collect(Collectors.toList());
            for (int i=1;i<args.keySet().size();i++ ){
                try {
                    req=req+"`"+rsmd.getColumnName(indexes.get(i-1))+"`=? AND " ;
                } catch (SQLException ex) {
                    Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.toString());
                }
            }
            try {
                    req=req+"`"+rsmd.getColumnName(indexes.get(indexes.size()))+"`=?" ;
                } catch (SQLException ex) {
                    Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println(ex.toString());
                }
        }
        PreparedStatement ps= cnx.prepareStatement(req);
        for (Map.Entry<Integer,Object> paire : args.entrySet()){
            try {
                ps.setObject(paire.getKey() ,paire.getValue() );
            } catch (SQLException ex) {
                Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
        }
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            Entity entity=new Entity();
            try {
                entity = e.getClass().newInstance();
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    values.add(rs.getObject(i));
                }
                entity.setValues(values);
                values.clear();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
            temp.add(entity);
        }
        ps.close();
        return temp;
    }
    
    public List<Entity> selectAll(Entity e,String tableName) throws SQLException,InstantiationException,IllegalAccessException {
        List<Entity> temp = new ArrayList<>();
        ArrayList <Object> values = new ArrayList();
        ResultSetMetaData rsmd= rsmd(e,tableName);
        String req = "SELECT * FROM `"+tableName+"`";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            Entity entity=new Entity();
            try {
                entity = e.getClass().newInstance();
                for(int i=1; i<=rsmd.getColumnCount();i++){
                    values.add(rs.getObject(i));
                }
                entity.setValues(values);
                values.clear();
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.toString());
            }
            temp.add(entity);
        }
        ps.close();
        return temp;
    }
    public void delete(Entity e,String tableName) throws SQLException,InstantiationException,IllegalAccessException{
        ResultSetMetaData rsmd= rsmd(e,tableName);
        String req = "SELECT * FROM `"+tableName+"` WHERE `"+rsmd.getColumnName(1)+"`="+e.getValues().get(0);
        PreparedStatement ps=cnx.prepareStatement(req);
        try {
            ps.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(MyMapper.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }
    }
}
