package com.pi.ati.ort.back.classes;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public static class DmnContext {
        private Construccion construccion;
        private Padron padron;

        public DmnContext() {
        }

        public DmnContext(Construccion construccion, Padron padron) {
            this.construccion = construccion;
            this.padron = padron;
        }

        public Construccion getConstruccion() {
            return construccion;
        }

        public Padron getPadron() {
            return padron;
        }
    }

    public static class Construccion {
        private int alturaConstruccion;

        public Construccion() {
        }

        public Construccion(int alturaConstruccion) {
            this.alturaConstruccion = alturaConstruccion;
        }

        public int getAlturaConstruccion() {
            return alturaConstruccion;
        }
    }

    public static class Padron {
        private int alturaMaxima;

        public Padron() {
        }

        public Padron(int alturaMaxima) {
            this.alturaMaxima = alturaMaxima;
        }

        public int getAlturaMaxima() {
            return alturaMaxima;
        }
    }
}