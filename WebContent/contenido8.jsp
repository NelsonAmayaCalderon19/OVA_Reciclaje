<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="http://code.jquery.com/jquery-1.12.0.js"></script> 
        <link rel="stylesheet" type="text/css" href="css/style_conten8.css">
        <title>Contenido 8</title>
    </head>
    <body>
        <div id="contenido">
            <div id="encabezado">
                <div id="bienv">
                    <h6 id="bienv_text">Manejo de Residuos Sólidos</h6>
                </div>
                <div id="navegacion">
                   <a href="contenido7.jsp" target="frame" id="anterior"><i class="fa fa-caret-left" id="caretant"></i> Anterior</a> <input id="campo" type="text" value="9/12" disabled=""/><a href="contenido9.jsp" target="frame" id="siguiente">Siguiente <i class="fa fa-caret-right" id="caret"></i></a>
                </div>
                </div>
            <div id="cuerpo">
                <div id="titulo">
                        <h1 id="aplicacion">Clasificación de los Residuos</h1>
                    </div>
                <div id="principal">
                    <div id="izquierda">
                    <div id="cabecera">
                    <h3 id="tit_cab">Orgánicos o Biodegradables</h3>
                </div>
                        <div id="cont">
                       <label id=""><strong><b>¿Qué Son?</b></strong></label>
                    <p>Son residuos que se pudren, pues se descomponen fácilmente al contacto con el medio natural.</p>
                    <label id=""><strong><b>¿Cuáles Son?</b></strong></label>
                    <p>Papel, cartón, sobrantes de comida, cáscaras y frutas, etc.</p>
               <label id=""><strong><b>¿Para qué Sirven?</b></strong></label><img id="img" src="images/comida-sana.png" alt=""/>
               <p>Para hacer abono o alimentos para animales.</p>              
                </div>
                </div>
                    <div id="derecha">
                    <div id="cabecera2">
                    <h3 id="tit_cab">Inorgánicos o No Biodegradables</h3>
                </div>
                        <div id="cont2">
                         <label id=""><strong><b>¿Qué Son?</b></strong></label>
                    <p>Residuos que permanecen en su estado y forma por largo tiempo, estos residuos pueden ser tóxicos y altamente contaminantes.</p>
                    <label id=""><strong><b>¿Cuáles Son?</b></strong></label>
                    <p>Plásticos, vidrio, icopor.</p>
               <label id=""><strong><b>¿Para qué Sirven?</b></strong></label><img id="img2" src="images/plastico.png" alt=""/>
                    <p>Para ser reutilizados y reciclados, siempre y cuando no sean tóxicos ni causen peligro al hombre y a la naturaleza.</p>              
                </div>
                </div>
                </div>
            </div>
        </div>
            <script src="js/contenido8.js" type="text/javascript"></script>
    </body>
</html>
