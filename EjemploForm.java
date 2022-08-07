package com.josekisystems.pooclasesabstractas.form;

import com.josekisystems.pooclasesabstractas.form.elementos.*;
import com.josekisystems.pooclasesabstractas.form.elementos.select.Opcion;
import com.josekisystems.pooclasesabstractas.form.validador.*;

import java.util.Arrays;
import java.util.List;

public class EjemploForm {
    public static void main(String[] args) {

        InputForm username = new InputForm("username", "nombre");
        username.addValidador(new RequeridoValidador());

        InputForm password = new InputForm("clave", "password");
        password.addValidador(new RequeridoValidador())
                .addValidador(new LargoValidador(6, 12));

        InputForm email = new InputForm("email", "email");
        email.addValidador(new RequeridoValidador())
                .addValidador(new EmailValidador());

        InputForm edad = new InputForm("edad", "number");
        edad.addValidador(new NumeroValidador());

        TextareaForm experiencia = new TextareaForm("exp", 5, 9);

        SelecForm lenguaje = new SelecForm("lenguaje");
        //Opcion java = new Opcion("1","Java");
        lenguaje.addValidador(new NoNuloValidador());

        lenguaje.addOpcion(new Opcion("1", "Java"))
                .addOpcion(new Opcion("2", "Phyton"))
                .addOpcion(new Opcion("3", "JavaScript"))//.setSelected()
                .addOpcion(new Opcion("4", "TypeScript"))
                .addOpcion(new Opcion("5", "PHP"));

        ElementoForm saludar = new ElementoForm("saludo") {
            @Override
            public String dibujarHtml() {
                return "<input disable name='" + this.nombre + "'" + "" +
                        "value=\"" + this.valor + "\">";
            }
        };

        saludar.setValor("Hola que tal este campo está desabilitado!");
        username.setValor("");//John.Doh
        password.setValor("");//a1b2c3
        email.setValor("");//john@corre.com
        edad.setValor("");//28
        experiencia.setValor("... mas de 10 años de experiencia...");
        //java.setSelected(true);

        List<ElementoForm> elementos = Arrays.asList(
                username,
                password,
                email,
                edad,
                experiencia,
                lenguaje,
                saludar);

        elementos.forEach(e -> {
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        });

        elementos.forEach(e -> {
            if (!e.esValido()) {
                e.getErrores().forEach(System.out::println );
                //err -> System.out.println(e.getNombre()+" : "+ err)
            }
        });
    }
}


/* ElementoForm el = new ElementoForm() {
            @Override
            public String dibujarHtml() {
                return null;
            }
        };
       // Cambio de For a Foreach
        for(ElementoForm e: elementos){
            System.out.println(e.dibujarHtml());
            System.out.println("<br>");
        
        */