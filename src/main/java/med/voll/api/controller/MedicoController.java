package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.dto.MedicoAtualizacaoDTO;
import med.voll.api.domain.medico.dto.MedicoCadastroDTO;
import med.voll.api.domain.medico.dto.MedicoDetalhesDTO;
import med.voll.api.domain.medico.model.Medico;
import med.voll.api.domain.medico.dto.MedicoDTO;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.domain.medico.service.ServiceMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

//    @PostMapping
//    public void cadastrar(@RequestBody String json) {
//        // ResquestBody pega o corpo do json para uma string, é obrigatório para conseguir ler um json
//        // se não vai ficar dando null
//      System.out.println(json);
//    }

    @Autowired
    ServiceMedico service;

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetalhesDTO> cadastrar(@RequestBody @Valid MedicoCadastroDTO dados, UriComponentsBuilder uriBuilder) {
        // VALID do Medico, para validar as informações com Bean Validation
        Medico medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        // buildAndExpand vai para {id}, ele substitui
        // .toUri() cria um URI

        /*
        Aqui ficaria salvo o endereço aonde estaria acessando o cadastrado, é necessário
        passar ele no Response.created que é 201, a gente tem que passar a URI que esse médico foi criado
        o endereço, que é por exemplo: localhost/medicos/1

        Mas como a gente não sabe aonde esse programa vai rodar, criamos uma UriComponentsBuilder, que gera
        automaticamente uma URI através do spring
         */

        return ResponseEntity.created(uri).body(new MedicoDetalhesDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> lista
            (@PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao) {
        Page<MedicoDTO> page = service.converteDados(paginacao);
        return ResponseEntity.ok(page);

        // .ok() TEM CONTEÚDO! passo a página como conteúdo

        // ANTIGAMENTE usavamos list e listavamos todos registros, agora listamos Page

        // @PageableDefault = padrão da página /medicos
        // size = tamanho de dados
        // page = página inicial
        // sort entre {}, dizendo pelo o que vai ordenar, baseado no atributo
    }

    // Page & Pageable são classes do spring boot que ordenam os dados em páginas
    // tanto aplicações mobile quanto web utilizam paginação

    // localhost:{numero}/medicos?size=1&page=0
    // ?size=1 -> indica que vai ter um dado por página
    // &page=0 -> indica a página atual

    // localhost:{numero}/medicos?sort=nome
    // ?sort -> ordena
    // nome -> nome está como atributo em MedicoDTO

    // localhost:{numero}/medicos?sort=crm,desc
    // ,desc -> ordena decrescente

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetalhesDTO> atualizar(@RequestBody @Valid MedicoAtualizacaoDTO dados) {
        var medico = repository.getReferenceById(dados.id());
        // busca pelo id
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new MedicoDetalhesDTO(medico));

        /* Medico é uma entidade JPA -> medico
        não é recomendado devolver entidades no controller, e sim um DTO
         */
    }

    // void devolve o código 200

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
//        repository.deleteById(id);
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();

        // ResponseEntity altera o código de status Http 200 - ok, 404 - not found
        // A gente pode retornar um código com algo como por exemplo:
        // ResponseEntity<MedicoDTO> ou vazio com Void, ou seja ele vai alterar o status
        // mas não vai ter nada de conteúdo retornando

        // .noContent() não é um objeto completo, precisa do build()
        // .ok() é um objeto completo não precisa
        // acessa a documentação para verificar se o objeto precisa ou não do ResponseEntity

    }

    @GetMapping("/{id}")
        public ResponseEntity<MedicoDetalhesDTO> detalhar(@PathVariable Long id) {
        return service.detalhar(id);
    }
}
