package com.marcos.foro.models.topicos;

import com.marcos.foro.models.curso.Curso;
import com.marcos.foro.models.respuesta.Respuesta;
import com.marcos.foro.models.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @Column(unique = true)
    private String mensaje;

    private LocalDateTime fecha;

    private Boolean estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Respuesta> respuestas;


    public Topico(DatosRegistroTopico datos, Usuario usuario, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = datos.fecha();
        this.estado = true;
        this.autor = usuario;
        this.curso = curso;
    }
}
