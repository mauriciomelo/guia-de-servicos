package br.gov.servicos.config;

import br.gov.servicos.metricas.Feedback;
import br.gov.servicos.servico.Servico;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import static br.gov.servicos.config.GuiaDeServicosIndex.GUIA_DE_SERVICOS;
import static lombok.AccessLevel.PRIVATE;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@FieldDefaults(level = PRIVATE)
public class GuiaDeServicosIndexTest {

    GuiaDeServicosIndex esConfig;

    @Mock
    ElasticsearchTemplate es;

    @Before
    public void setUp() {
        esConfig = new GuiaDeServicosIndex(es);
    }

    @Test
    public void deveApagarIndiceExistente() throws Exception {
        doReturn(true)
                .when(es)
                .indexExists(GUIA_DE_SERVICOS);

        esConfig.recriar();

        verify(es).deleteIndex(GUIA_DE_SERVICOS);
    }

    @Test
    public void deveCriarIndiceParaGuiaDeServicos() throws Exception {
        esConfig.recriar();

        verify(es).createIndex(eq(GUIA_DE_SERVICOS), anyString());
        verify(es).putMapping(Servico.class);
        verify(es).putMapping(Feedback.class);
    }

}