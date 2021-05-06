package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos Loaeza
 */
@WebServlet(name = "Ahorcado", urlPatterns = {"/Ahorcado"})
public class Ahorcado extends HttpServlet {

    private final static String[] PALABRAS1 = {"MUNDO", "ARBOL", "CASA"};
    private final static String[] PALABRAS2 = {"INTELIGENCIA", "MAQUINARIA", "COMPUTADORA"};
    private final static String[] PALABRAS3 = {"OTORRINOLARINGOLOGO", "ELECTROCARDIOGRAMA", "ELECTRODOMESTICO","ENCEFALOGRAMA"};
    private static int inten = 0;
    private static int dificultad=0;
    private static String nombre="";
 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        try{ 
        inten = Integer.parseInt((String) request.getParameter("nintento"));
        dificultad = Integer.parseInt(request.getParameter("grado"));
        nombre = (String) request.getParameter("nombre");
        }catch(NumberFormatException e){
        }
        String palabra = (String) sesion.getAttribute("palabra");
        
        String aciertos;
        String errados;
        if (palabra == null) {
             Random oran= new Random();            
             if(dificultad==1){
                 palabra = PALABRAS1[oran.nextInt(PALABRAS1.length-1)];  
             }else if(dificultad==2){
                 palabra = PALABRAS2[oran.nextInt(PALABRAS2.length-1)];  
             }else if(dificultad==3){
                 palabra = PALABRAS3[oran.nextInt(PALABRAS3.length-1)];  
             }
             aciertos="";            
             errados="";
             sesion.setAttribute("palabra",palabra);       
             sesion.setAttribute("aciertos",aciertos);            
             sesion.setAttribute("errados",errados); 
        } else {
             aciertos =(String) sesion.getAttribute("aciertos");             
             errados =(String) sesion.getAttribute("errados");             
             String letra= request.getParameter("letra");             
             if(palabra.indexOf(letra)>=0)               
                 aciertos+=letra;             
             else               
                 errados+=letra;             
             sesion.setAttribute("aciertos", aciertos);             
             sesion.setAttribute("errados", errados);
        }

        PrintWriter out = response.getWriter();

        try {
             out.println("<html>");             
             out.println("<head>");             
             out.println("<title>Servlet  juego ahorcado </title>");                         
             out.println("</head>");
             out.println("<body>");  
             out.println("<center>");
             out.println("<h2>Juego: " + request.getContextPath() + "</h2>"); 
             out.println("<h3>"+"Jugador: "+nombre+"</h3>");
             out.println("<h2>Probando suerte </h2>");             
             boolean terminado = true;             
             out.println("<h2>");
            for (int i = 0; i < palabra.length(); i++) {
                String letra = palabra.substring(i, i + 1);
                if (aciertos.indexOf(letra) >= 0) {
                    out.println("" + letra);
                } else {
                    out.println("" + "_");
                    terminado = false;
                }
            }
            out.println("</h2>");
            if (errados.length() < inten) {
                out.println("<br/><br/><br/>");
                for (char car = 'A'; car <= 'Z'; car++) {
                    if (aciertos.indexOf(car) == -1 && errados.indexOf(car) == -1) {
                        out.println("<a href='Ahorcado?letra=" + car + "'>" + car + "</a>");
                    }
                }
                out.println("<br/><h2>" + "Oportunidades de errar: " + (inten - errados.length()) + "</h2>");
            } else {
                sesion.invalidate();
                out.println("<br/><h3>JUEGO TERMINADO, PERDISTE 😕</h3>");
                out.println("<br/><a href='index.jsp'>regresa</a>");
            }
            if (terminado) {
                sesion.invalidate();
                out.println("<br/><h2>JUEGO COMPLETO, GANASTE 😃⭐</h2>");
                out.println("<br/><a href='index.jsp'>regresa</a>");
            }
            out.println("</center>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
