package br.gov.servicos.servico;

import br.gov.servicos.servico.linhaDaVida.LinhaDaVida;
import br.gov.servicos.servico.publicoAlvo.PublicoAlvo;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

import static br.gov.servicos.config.GuiaDeServicosIndex.GUIA_DE_SERVICOS;
import static lombok.AccessLevel.PRIVATE;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Object;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Value
@AllArgsConstructor(access = PRIVATE)
@Document(indexName = GUIA_DE_SERVICOS, type = "servico")
@XStreamAlias("servico")
@Wither
public class Servico {

    @Id
    String id;

    @Field(store = true, type = String)
    String titulo;

    @Field(store = true, type = String)
    String descricao;

    @Field(index = not_analyzed, type = String)
    String url;

    @Field(index = not_analyzed, type = String)
    String urlAgendamento;

    @Field(index = not_analyzed, type = String)
    String taxa;

    @Field(type = Object)
    Orgao prestador;

    @Field(type = Object)
    Orgao responsavel;

    @Field(index = not_analyzed, type = Object)
    List<PublicoAlvo> publicosAlvo;

    @Field(index = not_analyzed, type = Object)
    List<AreaDeInteresse> areasDeInteresse;

    @Field(index = not_analyzed, type = Object)
    List<LinhaDaVida> linhasDaVida;

    @Field(index = not_analyzed, type = String)
    List<String> eventosDasLinhasDaVida;

    public Servico() {
        this(null, null, null, null, null, null, null, null, null, null, null, null);
    }

}
