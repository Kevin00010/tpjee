package com.tpjee.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/AuthFilter")
public class AuthFilter  implements Filter {
       
	public static final String LOGIN_PAGE     = "/WEB-INF/login.jsp";
    public static final String ATT_SESSION_USER = "sessionUtilisateur";
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AuthFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		        /* Cast des objets request et response */
		        HttpServletRequest request = (HttpServletRequest) req;
		        HttpServletResponse response = (HttpServletResponse) res;

		        /* Récupération de la session depuis la requête */
		        HttpSession session = request.getSession();

		        /**
		         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
		         * l'utilisateur n'est pas connecté.
		         
		         */
		        
		        System.out.println(session.getAttribute("currentSessionUser"));
		        if ( session.getAttribute( "currentSessionUser" ) == null ) {
		            /* Redirection vers la page publique */
		            response.sendRedirect( request.getContextPath() + LOGIN_PAGE );
		        } else {
		            /* Affichage de la page restreinte */
		            chain.doFilter( request, response );
		        }
		// pass the request along the filter chain
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
