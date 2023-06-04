$(document).ready(function() {
	 moment.locale('pt-BR');
    $("#table-historico").DataTable({
        searching : false,
        processing: true,
        serverSide: true,
        responsive: true,
        lengthMenu: [5,10],
        order: [2, 'desc'],
        ajax: {
            url: "/avaliacoes/datatables/server",
           data:"data"
        },
        columns: [
            { data: "id" },
            { data: "identificacao" },
            { data: "altura" },
            { data: "peito" },
            { data: "peso" },
            { data: "cintura" },
            { data: "panturrilhaDireita" },
            { data: "panturrilhaEsquerda" },
            { data: "coxaDireita" },
            { data: "coxaEsqueda" },
            { data: "bracoEsquerdo" },
            { data: "bracoDireito" },
            { data: "antebracoEsquedo" },
            { data: "antebracoDireito" },
            { data: "gluteo" },
            { data: "imc" },
            {data: "dtInicio"},
            {data:"dtfim"}
,            
            {
                data: "dataInicio",
                render: function(datasAvaliacao) {
                    return moment(datasAvaliacao).format("LL");
                }
            },
           
          
        ]
    });
});