package com.pi.ati.ort.back.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

// DMN Request  --------------------------------------------------------------------------------------------
public class DmnRequest {
    @JsonProperty("model-namespace")
    private String modelNamespace;
    @JsonProperty("model-name")

    private String modelName;
    @JsonProperty("dmn-context")
    private DmnContext dmnContext;

    public DmnRequest() {
    }

    public DmnRequest(String modelNamespace, String modelName, DmnContext dmnContext) {
        this.modelNamespace = modelNamespace;
        this.modelName = modelName;
        this.dmnContext = dmnContext;
    }

    public String getModelNamespace() {
        return modelNamespace;
    }

    public String getModelName() {
        return modelName;
    }

    public DmnContext getDmnContext() {
        return dmnContext;
    }


    // DMN Context  --------------------------------------------------------------------------------------------
    public static class DmnContext {
        private Construccion construccion;
        private Padron padron;
        private Vivienda vivienda;
        private Bano bano;
        private Dormitorio dormitorio;

        public DmnContext() {
        }

        public DmnContext(Construccion construccion, Padron padron, Vivienda vivienda, Bano bano, Dormitorio dormitorio) {
            this.construccion = construccion;
            this.padron = padron;
            this.vivienda = vivienda;
            this.bano = bano;
            this.dormitorio = dormitorio;
        }

        public Construccion getConstruccion() {
            return construccion;
        }

        public Padron getPadron() {
            return padron;
        }

        public Vivienda getVivienda() {
            return vivienda;
        }

        public Bano getBano() {
            return bano;
        }

        public Dormitorio getDormitorio() {
            return dormitorio;
        }
    }

    // Construccion  --------------------------------------------------------------------------------------------
    public static class Construccion {
        private int alturaConstruccion;
        private int retiroDesdePlanoFachada;
        private boolean tieneBasamento;
        private int alturaBasamento;
        private int areaTotalConstruccionPlanta;
        private boolean tieneGalibo;
        private int alturaGalibo;
        private int retiroDeGaliboDePlanoFachada;
        private int lineaEdificacionConstruccionIzquierda;
        private int lineaEdificacionConstruccionDerecha;

        public Construccion() {
        }

        public Construccion(int alturaConstruccion, int retiroDesdePlanoFachada, boolean tieneBasamento, int alturaBasamento, int areaTotalConstruccionPlanta, boolean tieneGalibo, int alturaGalibo, int retiroDeGaliboDePlanoFachada, int lineaEdificacionConstruccionIzquierda, int lineaEdificacionConstruccionDerecha) {
            this.alturaConstruccion = alturaConstruccion;
            this.retiroDesdePlanoFachada = retiroDesdePlanoFachada;
            this.tieneBasamento = tieneBasamento;
            this.alturaBasamento = alturaBasamento;
            this.areaTotalConstruccionPlanta = areaTotalConstruccionPlanta;
            this.tieneGalibo = tieneGalibo;
            this.alturaGalibo = alturaGalibo;
            this.retiroDeGaliboDePlanoFachada = retiroDeGaliboDePlanoFachada;
            this.lineaEdificacionConstruccionIzquierda = lineaEdificacionConstruccionIzquierda;
            this.lineaEdificacionConstruccionDerecha = lineaEdificacionConstruccionDerecha;
        }

        public int getAlturaConstruccion() {
            return alturaConstruccion;
        }

        public int getRetiroDesdePlanoFachada() {
            return retiroDesdePlanoFachada;
        }

        public boolean isTieneBasamento() {
            return tieneBasamento;
        }

        public int getAlturaBasamento() {
            return alturaBasamento;
        }

        public int getAreaTotalConstruccionPlanta() {
            return areaTotalConstruccionPlanta;
        }

        public boolean isTieneGalibo() {
            return tieneGalibo;
        }

        public int getAlturaGalibo() {
            return alturaGalibo;
        }

        public int getRetiroDeGaliboDePlanoFachada() {
            return retiroDeGaliboDePlanoFachada;
        }

        public int getLineaEdificacionConstruccionIzquierda() {
            return lineaEdificacionConstruccionIzquierda;
        }

        public int getLineaEdificacionConstruccionDerecha() {
            return lineaEdificacionConstruccionDerecha;
        }
    }

    // Padron  --------------------------------------------------------------------------------------------
    public static class Padron {
        private int alturaMaxima;
        private boolean esFrentista;
        private int anchoVia;
        @JsonProperty("FOS")
        private int FOS;
        private int superficie;
        private int lineaEdificacionPadronDerecha;
        private int lineaEdificacionPadronIzquierda;

        public Padron() {
        }

        public Padron(int alturaMaxima, boolean esFrentista, int anchoVia, int FOS, int superficie, int lineaEdificacionPadronDerecha, int lineaEdificacionPadronIzquierda) {
            this.alturaMaxima = alturaMaxima;
            this.esFrentista = esFrentista;
            this.anchoVia = anchoVia;
            this.FOS = FOS;
            this.superficie = superficie;
            this.lineaEdificacionPadronDerecha = lineaEdificacionPadronDerecha;
            this.lineaEdificacionPadronIzquierda = lineaEdificacionPadronIzquierda;
        }

        public int getAlturaMaxima() {
            return alturaMaxima;
        }

        public boolean getEsFrentista() {
            return esFrentista;
        }

        public int getAnchoVia() {
            return anchoVia;
        }

        public int getFOS() {
            return FOS;
        }

        public int getSuperficie() {
            return superficie;
        }

        public int getLineaEdificacionPadronDerecha() {
            return lineaEdificacionPadronDerecha;
        }

        public int getLineaEdificacionPadronIzquierda() {
            return lineaEdificacionPadronIzquierda;
        }
    }

    // Vivienda  --------------------------------------------------------------------------------------------
public static class Vivienda {
        public int cantBano;
        public int cantCocina;
        public int cantEstar;
        public int cantDormitorio;
        public int areaTotal;

        public Vivienda() {
        }

        public Vivienda(int cantBano, int cantCocina, int cantEstar, int cantDormitorio, int areaTotal) {
            this.cantBano = cantBano;
            this.cantCocina = cantCocina;
            this.cantEstar = cantEstar;
            this.cantDormitorio = cantDormitorio;
            this.areaTotal = areaTotal;
        }

        public int getCantBano() {
            return cantBano;
        }

        public int getCantCocina() {
            return cantCocina;
        }

        public int getCantEstar() {
            return cantEstar;
        }

        public int getCantDormitorio() {
            return cantDormitorio;
        }

        public int getAreaTotal() {
            return areaTotal;
        }
    }

    // Bano  --------------------------------------------------------------------------------------------
    public static class Bano {
        public boolean independencia;

        public Bano() {
        }

        public Bano(boolean independencia) {
            this.independencia = independencia;
        }

        public boolean isIndependencia() {
            return independencia;
        }
    }

    // Dormitorio  --------------------------------------------------------------------------------------------
    public static class Dormitorio {
        public boolean independencia;

        public Dormitorio() {
        }

        public Dormitorio(boolean independencia) {
            this.independencia = independencia;
        }

        public boolean isIndependencia() {
            return independencia;
        }
    }
}