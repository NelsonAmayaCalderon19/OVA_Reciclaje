package control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelDAO.AlumnoDAO;
import modelDAO.ColegioDao;
import modelo.Alumno;
import modelo.Colegio;

/**
 * Servlet implementation class Certificado
 */
@WebServlet("/Certificado")
public class Certificado extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Alumno alm = new Alumno();
	AlumnoDAO almdao = new AlumnoDAO();
	Colegio colegio = new Colegio();
	ColegioDao coldao = new ColegioDao();
	int dato=0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Certificado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");
         PrintWriter out;
         out = response.getWriter();
            switch (accion){
                case "ingresar":          
		String id=request.getParameter("id");
                if(id==""){
                    out.println("<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.11.4/sweetalert2.all.js'></script>");
            out.println("<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>");
            out.println("<script>");
            out.println("$(document).ready(function(){");
            out.println("swal ('Error','Debes Seleccionar tu Colegio','error' )");
            out.println("});");
            out.println("</script>");
            RequestDispatcher rd3=request.getRequestDispatcher("index.jsp");
rd3.include(request, response);
                }else{
colegio = coldao.buscar(String.valueOf(id));
                    request.setAttribute("colegio", colegio);
//                     PrintWriter pw2 = response.getWriter();
//                        pw2.println("<script type=\"text/javascript\">");
//pw2.println("alert('"+col+"');");
//pw2.println("</script>"); 
                    RequestDispatcher rd2=request.getRequestDispatcher("primaria.jsp");
rd2.include(request, response);
                }
    	  break;
                default:
                    throw new AssertionError();
            }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion");

        String menu = request.getParameter("menu");
    if(menu.equalsIgnoreCase("Certificar")){   
      
//                    almdao.nuevo_Certificado(dato);
            switch (accion){
                case "Aceptar":
                    String col = request.getParameter("txtColegio");
                    colegio = coldao.buscar(String.valueOf(col));
                    request.setAttribute("colegio", colegio);
//                     PrintWriter pw2 = response.getWriter();
//                        pw2.println("<script type=\"text/javascript\">");
//pw2.println("alert('"+col+"');");
//pw2.println("</script>"); 
                    RequestDispatcher rd2=request.getRequestDispatcher("index.jsp");
rd2.include(request, response);
                    break;
                    
                case "Listar":
                     List lista = almdao.listar();
                   request.setAttribute("e", lista);
                    break;
                case "Generar":
                    
                    String id = request.getParameter("tarjeta2");
                    String nombre = request.getParameter("nombre2");
                    if(almdao.buscar_Num_cert(1, id)==true){
                  
                    PrintWriter pw = response.getWriter();
                        pw.println("<script type=\"text/javascript\">");
pw.println("alert('Usted ya Fue Certificado anteriormente en este Nivel');");
pw.println("</script>");                  
RequestDispatcher rd=request.getRequestDispatcher("primaria.jsp");
rd.include(request, response);

                    }else{
                       dato=almdao.buscar_Num_cert2(); 
                    almdao.nuevo_Certificado(dato+1);
                    dato=almdao.buscar_Num_cert2();
                    alm.setIdentificacion(id);
                    alm.setNombre(nombre);
                    alm.setTipo_doc(1);
                    alm.setId_Colegio(colegio.getId());
                    almdao.agregar(alm);
                    almdao.descripcion_Certificado(dato,alm);
                     response.setContentType("application/pdf");
                    OutputStream out = response.getOutputStream();
          try{
          Document documento = new Document(PageSize.A4.rotate());
               PdfWriter.getInstance(documento, out);       
documento.open();
               documento.open(); 
               com.itextpdf.text.Image imag = com.itextpdf.text.Image.getInstance("https://i.pinimg.com/originals/67/8d/29/678d2976771ea52cd77a54591be4eff7.png");   
        imag.setAbsolutePosition(0, 0);
        imag.scaleToFit(842,595);
        documento.add(imag);
               Paragraph info = new Paragraph();
               Paragraph info2 = new Paragraph();
               Paragraph info9 = new Paragraph();
               Paragraph info10 = new Paragraph();
               com.itextpdf.text.Image imagenes = com.itextpdf.text.Image.getInstance(""+colegio.getImagen()+"");   
        imagenes.setAbsolutePosition(90, 440);
        imagenes.scaleToFit(100,100);
        documento.add(imagenes);
               Font fontinfo = new Font(Font.FontFamily.TIMES_ROMAN,13,Font.NORMAL,BaseColor.BLACK);
               Font fontinfo2 = new Font(Font.FontFamily.TIMES_ROMAN,16,Font.NORMAL,BaseColor.BLACK);
        info.add(new Phrase(Chunk.NEWLINE));
        info.add(new Phrase(Chunk.NEWLINE));
        info.add(new Phrase(Chunk.NEWLINE));
        info.add(new Phrase(""+colegio.getNombre()+"",fontinfo2));

        info.setAlignment(Element.ALIGN_CENTER);  
        info2.add(new Phrase(Chunk.NEWLINE));
        info2.add(new Phrase("Objeto Virtual de Aprendizaje - Manejo de Residuos S�lidos",fontinfo2));
        info2.add(new Phrase(Chunk.NEWLINE));
        info.setAlignment(Element.ALIGN_CENTER);       
        info2.setAlignment(Element.ALIGN_CENTER);
        info.add(new Phrase(Chunk.NEWLINE));

        documento.add(info);
        documento.add(info2);
//        documento.add(new Phrase(Chunk.NEWLINE));
//        documento.add(new Phrase(Chunk.NEWLINE));
        documento.add(new Phrase(Chunk.NEWLINE));
               Paragraph par1 = new Paragraph();
               Font fonttitulo = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD,BaseColor.BLACK);
              
        par1.add(new Phrase("Certificado N�mero: "+dato,fonttitulo)+"         ");
        par1.setAlignment(Element.ALIGN_RIGHT);
        par1.add(new Phrase(Chunk.NEWLINE)); 
        par1.add(new Phrase(Chunk.NEWLINE)); 
        documento.add(par1);
        Paragraph par2 = new Paragraph();
        Font fonttitulo2 = new Font(Font.FontFamily.HELVETICA,20,Font.BOLD,BaseColor.BLACK);
        documento.add(new Phrase(Chunk.NEWLINE));
        documento.add(new Phrase(Chunk.NEWLINE));
        documento.add(new Phrase(Chunk.NEWLINE));
          par2.add(new Phrase("CERTIFICACI�N ",fonttitulo2));
        par2.setAlignment(Element.ALIGN_CENTER);     
            documento.add(par2);   
            documento.add(new Phrase(Chunk.NEWLINE));
        Paragraph par3 = new Paragraph();
         Paragraph par4 = new Paragraph();
        Font fonttitulo3 = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.BOLD,BaseColor.BLACK);
        Font fonttitulo4 = new Font(Font.FontFamily.TIMES_ROMAN,14,Font.NORMAL,BaseColor.BLACK);
        par3.add(new Phrase("Certifica que: ",fonttitulo3));
        par3.add(new Phrase(nombre,fonttitulo4));
        par3.setAlignment(Element.ALIGN_CENTER); 
        documento.add(new Phrase(Chunk.NEWLINE));
        par4.add(new Phrase("Identificado con TI. No. "+id,fonttitulo4));
        par4.setAlignment(Element.ALIGN_CENTER); 
        documento.add(par3); 
        documento.add(par4); 
        Paragraph par5 = new Paragraph();
        Font fonttitulo5 = new Font(Font.FontFamily.TIMES_ROMAN,13,Font.NORMAL,BaseColor.BLACK);
        documento.add(new Phrase(Chunk.NEWLINE));
        par5.add(new Phrase("Por haber Aprobado el Objeto Virtual de Aprendizaje (OVA)",fonttitulo5));
        documento.add(new Phrase(Chunk.NEWLINE));
        par5.add(new Phrase(" en el Manejo de Residuos S�lidos - Nivel B�sica Primaria",fonttitulo5));
        par5.setAlignment(Element.ALIGN_CENTER); 
        documento.add(par5); 
        documento.add(new Phrase(Chunk.NEWLINE));
        Paragraph fecha = new Paragraph();
               Font fontfecha = new Font(Font.FontFamily.TIMES_ROMAN,12,Font.NORMAL,BaseColor.BLACK);
                Calendar fech = new GregorianCalendar();
        int a�o = fech.get(Calendar.YEAR);
        int mes = fech.get(Calendar.MONTH);
        int dia = fech.get(Calendar.DAY_OF_MONTH);
        String[] Array = new String[]{"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre" }; 
        fecha.add(new Phrase("         Fecha de Certificaci�n: "+dia + " de " + Array[mes] + " de " + a�o,fontfecha));
        fecha.setAlignment(Element.ALIGN_LEFT);
        fecha.add(new Phrase(Chunk.NEWLINE));       
        documento.add(fecha);        
        documento.add(new Phrase(Chunk.NEWLINE)); 
//         com.itextpdf.text.Image imagen = com.itextpdf.text.Image.getInstance("https://lh4.googleusercontent.com/Wmg48NC62oduaoMPk8cLgKft85Tu-niSaCKasS8-br2AssvwaUcK-bH_zwppTDfFU0VDqTPVmY6BKiQfJsGgY0FMjOROgY_fsfaeNg96Bumwaf1knQ=w1280");   
//        imagen.setAbsolutePosition(140, 100);
//        imagen.scaleToFit(200,150);
//        documento.add(imagen);
//         com.itextpdf.text.Image imagene = com.itextpdf.text.Image.getInstance("https://lh3.googleusercontent.com/Xcbm2mosUZ5a-k6KJfpSKyhopi6i8mFkyE8bdgxna41NC2OIoT3OcSrXH7w05JJjZrBiLlcOjLsAVgM2Mip52fOWKrlmAIH7C8_-OuwbBJYHOPDJHLIQ=w1280");   
//        imagene.setAbsolutePosition(550, 100);
//        imagene.scaleToFit(200,150);
//        documento.add(imagene);
        documento.close();
        RequestDispatcher rd=request.getRequestDispatcher("primaria.jsp");
rd.include(request, response);
          }catch(Exception e){
          } 
                    }
                    break;
                
                    default:
                        
                    throw new AssertionError();
            }   
            
    }

	}

}
