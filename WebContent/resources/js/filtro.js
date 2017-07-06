//Data table
$(document).ready(function() {
	$('#table_id').DataTable({
		"language" : {
			"url" : "resources/idioma/Portuguese-Brasil.json"
		}
	});
});

// Filtro
$(document).ready(function() {
	$('#filtro').click(function() {
		$.ajax({
			type : "post",
			url : "filtrarImpressoes",
			cache : false,
			data : {
				nome_impressora : $("#impressora :selected").text(),
				nome_estacao : $("#estacao").val()
			},
			success : function(response) {
				$('#tabela').html(response);
				atualiza();
			},
			error : function() {
				alert('Ocorreu um erro!');
			}
		});
	});
});

// Atualiza data table
function atualiza() {
	$('#table_id').DataTable({
		"language" : {
			"url" : "resources/idioma/Portuguese-Brasil.json"
		}
	});
}