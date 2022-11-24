package com.example.scpp;

public class Projeto {

    private String projeto;
    private String professor;
    private double capitalPrevisto;
    private double capitalGasto;
    private double materialPrevisto;
    private double materialGasto;
    private double servicoPfPrevisto;
    private double servicoPfGasto;
    private double servicoPjPrevisto;
    private double servicoPjGasto;
    private double diariasPrevisto;
    private double diariasGasto;
    private double passagensPrevisto;
    private double passagensGasto;
    private String listaDespesas;

    public Projeto(String projeto, String professor, double capitalPrevisto, double capitalGasto, double materialPrevisto, double materialGasto, double servicoPfPrevisto, double servicoPfGasto, double servicoPjPrevisto, double servicoPjGasto, double diariasPrevisto, double diariasGasto, double passagensPrevisto, double passagensGasto, String listaDespesas) {
        this.projeto = projeto;
        this.professor = professor;
        this.capitalPrevisto = capitalPrevisto;
        this.capitalGasto = capitalGasto;

        this.materialPrevisto = materialPrevisto;
        this.materialGasto = materialGasto;

        this.servicoPfPrevisto = servicoPfPrevisto;
        this.servicoPfGasto = servicoPfGasto;

        this.servicoPjPrevisto = servicoPjPrevisto;
        this.servicoPjGasto = servicoPjGasto;

        this.diariasPrevisto = diariasPrevisto;
        this.diariasGasto = diariasGasto;

        this.passagensPrevisto = passagensPrevisto;
        this.passagensGasto = passagensGasto;

        this.listaDespesas = listaDespesas;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public double getCapitalPrevisto() {
        return capitalPrevisto;
    }

    public void setCapitalPrevisto(double capitalPrevisto) {
        this.capitalPrevisto = capitalPrevisto;
    }

    public double getCapitalGasto() {
        return capitalGasto;
    }

    public void setCapitalGasto(double capitalGasto) {
        this.capitalGasto = capitalGasto;
    }

    public double getMaterialPrevisto() {
        return materialPrevisto;
    }

    public void setMaterialPrevisto(double materialPrevisto) {
        this.materialPrevisto = materialPrevisto;
    }

    public double getMaterialGasto() {
        return materialGasto;
    }

    public void setMaterialGasto(double materialGasto) {
        this.materialGasto = materialGasto;
    }

    public double getServicoPfPrevisto() {
        return servicoPfPrevisto;
    }

    public void setServicoPfPrevisto(double servicoPfPrevisto) {
        this.servicoPfPrevisto = servicoPfPrevisto;
    }

    public double getServicoPfGasto() {
        return servicoPfGasto;
    }

    public void setServicoPfGasto(double servicoPfGasto) {
        this.servicoPfGasto = servicoPfGasto;
    }

    public double getServicoPjPrevisto() {
        return servicoPjPrevisto;
    }

    public void setServicoPjPrevisto(double servicoPjPrevisto) {
        this.servicoPjPrevisto = servicoPjPrevisto;
    }

    public double getServicoPjGasto() {
        return servicoPjGasto;
    }

    public void setServicoPjGasto(double servicoPjGasto) {
        this.servicoPjGasto = servicoPjGasto;
    }

    public double getDiariasPrevisto() {
        return diariasPrevisto;
    }

    public void setDiariasPrevisto(double diariasPrevisto) {
        this.diariasPrevisto = diariasPrevisto;
    }

    public double getDiariasGasto() {
        return diariasGasto;
    }

    public void setDiariasGasto(double diariasGasto) {
        this.diariasGasto = diariasGasto;
    }

    public double getPassagensPrevisto() {
        return passagensPrevisto;
    }

    public void setPassagensPrevisto(double passagensPrevisto) {
        this.passagensPrevisto = passagensPrevisto;
    }

    public double getPassagensGasto() {
        return passagensGasto;
    }

    public void setPassagensGasto(double passagensGasto) {
        this.passagensGasto = passagensGasto;
    }

    public String getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(String listaDespesas) {
        this.listaDespesas = listaDespesas;
    }
}
