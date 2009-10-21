<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="cz.horcica.todoizer.SecurityHelper"%><html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Todoizer</title>
        
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="/static/style/main.css" type="text/css">
        
    </head>
    <body>
        
        <div id="frame">
        
            <div id="login">
                <a href="/">Zpět</a>
            </div>
            
            <%@ include file="parts/header.html" %>
            
            <div id="help">
                <h2>Nápověda</h2>
                
                <p>
                    <strong>Todoizer</strong> je jednoduchý on-line úkolovník. 
                </p>

                <h3>Základní vlastnosti</h3>
                
                <ul>
                    <li>Umožňuje vést si seznam úkolů</li>
                    <li>Každý úkol může být označen libovolným množstvím štítků (tagů)</li>
                    <li>Podle štítků se dá filtrovat (kliknutím na štítek, nebo zadáním názvu do pole filtru)</li>
                    <li>Každý úkol může být označený jako splněný/nesplněný</li>
                    <li>Úkoly můžete vkládat pomocí webového rozhraní, e-mailu nebo Google Talk</li>
                </ul>
                
                <h3>Podpora pro e-mail</h3>
                
                <p>Do svého seznamu můžete vložit nový úkol posláním e-mailu na adresu <code>todoizer@todoizer.appspotmail.com</code>. 
                Nový úkol bude vytvořen ze předmětu zprávy (případné „Re“ a „Fwd“ uvození bude ignorováno).</p>
                
                <p>E-mail musíte odeslat ze svého e-mailového účtu, pod kterým používáte Todoizer.</p>
                
                <h3>Podpora pro Google Talk</h3>
                
                <p>Přidejte si do Google Talku kontakt <code>todoizer@appspot.com</code>. Každá zpráva kterou pošlete na tento kontakt, bude vložena jako nový úkol.</p>
                
                <p>Seznam úkolů je možné získat posláním „<code>?</code>“.</p>
                
                <p>Komunikovat musíte ze svého účtu, pod kterým používáte Todoizer.</p>
                
                <h2>O aplikaci</h2>
                
                <p>Todoizer je soutěžní aplikace do soutěže <a href="http://wiki.gug.cz/pro-vyvojare-1/online-dilna-gugcz">Online dílny GUG.CZ</a> – říjen 2009.</p>
                
                <p>Autorem aplikace je <a href="mailto:horcicaa@gmail.com">Adam Hořčica</a>. Zdrojové kódy aplikace jsou dostupné na <a href="http://github.com/ah01/Todoizer">github</a>u.</p>
                
                <table width="100%" style="text-align:center">
                    <tr>
                        <td width="50%"><a href="http://gug.cz"><img src="/static/gug.png" alt="GUG.cz"></a></td>
                        <td><img src="http://code.google.com/appengine/images/appengine-silver-120x30.gif" alt="Powered by Google App Engine" /></td>
                    </tr>
                </table>
                
                
            </div>
        </div>

        <%@ include file="parts/ga.html" %>

    </body>
</html>