package com.josekisystems.pooclasesabstractas.form.elementos;

import com.josekisystems.pooclasesabstractas.form.validador.LargoValidador;
import com.josekisystems.pooclasesabstractas.form.validador.Validador;
import com.josekisystems.pooclasesabstractas.form.validador.mensaje.MensajeFormateable;

import java.util.ArrayList;
import java.util.List;

abstract public  class ElementoForm {

    protected String valor;
    protected String nombre;

    private List<Validador> validadores;
    private List<String> errores;

    public ElementoForm() {
        this.validadores = new ArrayList<>();
        this.errores = new ArrayList<>();

    }

    public ElementoForm(String nombre) {
        this();
        this.nombre = nombre;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public ElementoForm addValidador(Validador validador){
        this.validadores.add(validador);
        return this;
    }

    public List<String> getErrores() {
        return errores;
    }

    //public String getNombre() {return nombre;}

    public boolean esValido(){
        for(Validador v: validadores){
            if(!v.esValido(this.valor)) {
                if (v instanceof MensajeFormateable) {
                    this.errores.add(((MensajeFormateable) v).getMensajeFormateado(nombre));
                } else {
                    this.errores.add(String.format(v.getMensaje(), nombre));
                }
            }
        }
        return this.errores.isEmpty();
    }
    abstract public String dibujarHtml();

}
/*
 if (v instanceof LargoValidador) {
                    this.errores.add(((LargoValidador) v).getMensajeFormateado(nombre));
                } else {
                    this.errores.add(String.format(v.getMensaje(), nombre));
                }
 */