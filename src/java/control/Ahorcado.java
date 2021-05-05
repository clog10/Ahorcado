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

    private final static String[] PALABRAS = {"ACERTASTE", "OAXACA", "HAMACA", "AHORITA"};
    private static int inten = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        String nombre = (String) request.getParameter("nombre");
        try {
            inten = Integer.parseInt((String) request.getParameter("nintento"));
        } catch (NumberFormatException e) {
            String palabra = (String) sesion.getAttribute("palabra");
            String aciertos;
            String errados;
            if (palabra == null) {
                Random oran = new Random();
                palabra = PALABRAS[oran.nextInt(PALABRAS.length - 1)];
                aciertos = "";
                errados = "";
                sesion.setAttribute("palabra", palabra);
                sesion.setAttribute("aciertos", aciertos);
                sesion.setAttribute("errados", errados);
            } else {
                aciertos = (String) sesion.getAttribute("aciertos");
                errados = (String) sesion.getAttribute("errados");
                String letra = request.getParameter("letra");
                if(palabra.indexOf(letra)>=0){
                    aciertos+=letra;
                }else{
                    errados+=letra;
                }
                sesion.setAttribute("aciertos",aciertos);
                sesion.setAttribute("errados", errados);
            }
            
            PrintWriter out = response.getWriter();
            
            try{
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Ahorcado</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<h2>Juego</h2>");
                out.println("<h2>"+nombre+"</h2>");
                out.println("<h3>Selecciona una letra</h3>");
                boolean terminado = false;
                out.println("<h2>");
                for(int i=0; i<palabra.length();i++){
                    String letra = palabra.substring(i,i+1);
                    if(aciertos.indexOf(letra)>=0){
                        out.println(""+letra);
                    }else{
                        out.println(""+"_");
                        terminado = false;
                    }
                }
                out.println("</h2>");
                if(errados.length()<inten){
                    out.println("<br/><br/><br/>");
                    for(char car='A'; car<='Z'; car++){
                        if(aciertos.indexOf(car)==-1 && errados.indexOf(car)==-1)
                            out.println("<a href='Ahorcado?letra="+car+"'>"+car+"</a>");        
                    }
                    out.println("<br/><h2>"+"Oportunidades de errar: "+(inten-errados.length())+"</h2>");
                }else{
                    sesion.invalidate();
                    out.println("<br/><h3>JUEGO TERMINADO</h3>");
                    out.println("<br/><a href='index.jsp'>regresa</a>");
                }
                if(terminado){
                    sesion.invalidate();
                    out.println("<br/><h2>JUEGO TERMINADO</h2>");
                    out.println("<br/><a href='index.jsp'>regresa</a>");
                }
            }catch(Exception eeee){
                
            }
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
