package filter;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 
 
@WebFilter(filterName = "AuthFilter", urlPatterns = {"/*"})
public class AuthFilter implements Filter {
     
    public AuthFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
         
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         try {
  
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession ses = req.getSession(false);
            String rep="";
        
            String reqURI = req.getRequestURI();  
               
        if ( (!reqURI.contains("/Abus") && 
                    !reqURI.contains("/EditerEmploye") &&   
                    !reqURI.contains("/EditerUtilisateu")&& 
                    !reqURI.contains("/Gestion_Boutique") && 
                    !reqURI.contains("/Gestion_Employees") && 
                    !reqURI.contains("/Gestion_Utilisateurs") && 
                    !reqURI.contains("/Gray") && 
                    !reqURI.contains("/Live_chat") && 
                    !reqURI.contains("/Modified") && 
                    !reqURI.contains("/ModifiedAll") && 
                    !reqURI.contains("/NewUser") && 
                    !reqURI.contains("/NewUserAll") && 
                    !reqURI.contains("/Queus") && 
                    !reqURI.contains("/Reclamations") && 
                    !reqURI.contains("/Saif_Mail") && 
                    !reqURI.contains("/Search") && 
                    !reqURI.contains("/Spam_Filter") && 
                    !reqURI.contains("/Web_Sql") && 
                    !reqURI.contains("/conversation") && 
                    !reqURI.contains("/editAnnoncePanel") && 
                    !reqURI.contains("/grayAll") && 
                    !reqURI.contains("/Menu") && 
                    !reqURI.contains("/nouveauEmp")  
                    )   || (ses != null && ses.getAttribute("emp") != null)
                                       || reqURI.contains("javax.faces.resource") ){
                  
                if ( (!reqURI.contains("/Chat") || 
                    ! reqURI.contains("/MesAnnonces") ||
                     !reqURI.contains("/Publication")|| 
                    ! reqURI.contains("/MaBoutique")|| 
                    ! reqURI.contains("/Modifier_annonce")|| 
                     !reqURI.contains("/FirstMessage") || 
                     !reqURI.contains("/Publication")
                    )){rep="o";
                }
                 }
                
            else   { rep="p";} 
        
        
        if ( (!reqURI.contains("/Chat") && 
                    !reqURI.contains("/MesAnnonces") &&
                    !reqURI.contains("/Publication") && 
                    !reqURI.contains("/MaBoutique")&& 
                    !reqURI.contains("/Modifier_annonce")&& 
                    !reqURI.contains("/FirstMessage") && 
                    !reqURI.contains("/Publication")
                    )   || (ses != null && ses.getAttribute("user") != null)
                                       || reqURI.contains("javax.faces.resource") ){
                if(!rep.equals("p"))  { rep="o";}}
            else   {rep="i";  } 
                
          
       
          
      
         
         
         if("o".equals(rep)){
             chain.doFilter(new XssRequestWrapper( (HttpServletRequest) request), response);
         }else if("p".equals(rep)){
             res.sendRedirect(req.getContextPath() + "/Panel"); 
             
         }else if("i".equals(rep)){
             res.sendRedirect(req.getContextPath() + "/Inscription");
         }}
     catch(Throwable t) {
         System.out.println( t.getMessage());
     }
    }  
 
    @Override
    public void destroy() {
         
    }
    
    
     private static class XssRequestWrapper extends HttpServletRequestWrapper {


        private static final Pattern [] XSS_PATTERNS = {
                Pattern.compile( "<.*?>" ),
                Pattern.compile( "&.*?;" ),
                Pattern.compile( "%[0-9a-fA-F]*" )
        };
        

        public XssRequestWrapper(HttpServletRequest servletRequest) {
            super(servletRequest);
        }

        @Override
        public String[] getParameterValues( String parameterName ) {
            
            String [] values = super.getParameterValues(parameterName);

            if (values == null) return null;

            int count = values.length;
            for ( int i = 0; i < count; i++ ) {
                // On remplace chaque chaîne de caractères
                values[i] = removeTags(values[i]);
            }

            return values;
        }

        @Override
        public String getParameter( String parameter ) {
            return removeTags( super.getParameter(parameter) );
        }

        @Override
        public String getHeader( String name ) {
            return removeTags( super.getHeader(name) );
        }

        
        private String removeTags( String value ) {
            if ( value != null ) {
                // On retire le code ASCII 0, au cas ou
                value = value.replaceAll( "\0", "" );

                // Supprime l'ensemble de tags et des entités existants
                for ( Pattern pattern : XSS_PATTERNS ) {
                    value = pattern.matcher( value ).replaceAll( "" );
                }
                
                // Au cas ou les caractères < et > ne seraient pas en nombres pairs
                value = value.replaceAll( "<", "" );
                value = value.replaceAll( ">", "" );
            }
            return value;
        }
    }

}