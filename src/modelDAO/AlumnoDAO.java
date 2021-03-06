/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Alumno;

/**
 *
 * @author NELSON
 */
public class AlumnoDAO {
    Conexion cn = new Conexion();
    Connection con = cn.Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
     public int agregar(Alumno c){
    String sql="insert into alumno(identificacion, nombre, id_tipo,id_Colegio, fecha) values(?,?,?,?,NOW())";
    try{
    ps=con.prepareStatement(sql);
    ps.setString(1, c.getIdentificacion());
    ps.setString(2, c.getNombre());
    ps.setInt(3, c.getTipo_doc());
    ps.setInt(4, c.getId_Colegio());
    ps.executeUpdate();
    }catch(Exception ex){
    }
    return r;
    }
      public int nuevo_Certificado(int dato){
    String sql="insert into certificado(numero) values("+dato+")";
    try{        
    ps=con.prepareStatement(sql);
    ps.executeUpdate();
    }catch(Exception ex){
    }
    return r;
    }
      
      public int descripcion_Certificado(int dato, Alumno a){
    String sql="insert into cert_desc(numero_cert,id_alumno,id_nivel,fecha) values("+dato+",?,?,NOW())";
    try{        
    ps=con.prepareStatement(sql);
    ps.setString(1, a.getIdentificacion());
    ps.setInt(2, a.getTipo_doc());
    ps.executeUpdate();
    }catch(Exception ex){
    }
    return r;
    }
       public boolean buscar_Num_cert(int nivel,String id){
    String sql="select * from cert_desc where id_nivel="+nivel+" and id_alumno="+id+"";
    boolean res = false;
    try{
    ps=con.prepareStatement(sql);
    rs=ps.executeQuery();
    while(rs.next()){
    res=true;
    }
    }catch(Exception e){    
    }
    return res;
    }
       public int buscar_Num_cert2(){
    String sql="select * from certificado order by numero desc limit 1";
    try{
    ps=con.prepareStatement(sql);
    rs=ps.executeQuery();
    while(rs.next()){
    r=rs.getInt(1);
    }
    }catch(Exception e){    
    }
    return r;
    }
       public List listar(){
    String sql="select * from pregunta ";
    List<Alumno> lista = new ArrayList<>();
    try{
    con=cn.Conexion();
    ps=con.prepareStatement(sql);
    rs=ps.executeQuery();
    while(rs.next()){
    Alumno t = new Alumno();
    lista.add(t);
    }
    }catch(Exception e){
    }
    return lista;
    }
}
