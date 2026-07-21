package com.farmaciasalud.dto.response;
import java.util.List;

public class PageResponse<T> {
    private List<T> contenido;
    private int paginaActual;
    private int totalPaginas;
    private long totalElementos;
    private int tamanioPagina;
    private boolean esUltimaPagina;

    public PageResponse(List<T> contenido, int paginaActual, int totalPaginas,
                        long totalElementos, int tamanioPagina) {
        this.contenido = contenido;
        this.paginaActual = paginaActual;
        this.totalPaginas = totalPaginas;
        this.totalElementos = totalElementos;
        this.tamanioPagina = tamanioPagina;
        this.esUltimaPagina = paginaActual >= (totalPaginas - 1);
    }
    public List<T> getContenido() { return contenido; }
    public int getPaginaActual() { return paginaActual; }
    public int getTotalPaginas() { return totalPaginas; }
    public long getTotalElementos() { return totalElementos; }
    public int getTamanioPagina() { return tamanioPagina; }
    public boolean isEsUltimaPagina() { return esUltimaPagina; }
}
