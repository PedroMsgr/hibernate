package es.severo.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tramite")
public class Tramite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_tramite")
    private String tipo;

    @Column(name = "fecha_tramite")
    private LocalDateTime fecha;

    public Tramite() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Tramite{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}
