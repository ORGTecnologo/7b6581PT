function onlyNumbersDano(evt){
//Solo admite el ingreso de numeros
    var keyPressed = (evt.which) ? evt.which : event.keyCode
            return !(keyPressed > 31 && (keyPressed < 48 || keyPressed > 57));
}

function Pintar_hasError(input){
//Esta funcion pinta de rojo el input
	if (input.value == ""){
		input.parentElement.setAttribute('class','has-error');
		return true;
	}
	input.parentElement.setAttribute('class','has-succes');
		return false;
}

function setRegistroTipo(tipo){
	varsProy.tipoRegistro = tipo;
}

function mostrarElemento(id){

	var div = $("#" + id);
	div.show();

	switch(id){
		case 'registroUsuario':
			if (varsProy.tipoRegistro == 'proveedor')
				document.getElementById('sitioWeb_p').setAttribute('style','visibility: visible;');
			else	
				document.getElementById('sitioWeb_p').setAttribute('style','visibility: hidden;');
		break;
	}


}

function ocultarElemento(id){
	var div = $("#" + id);
	div.hide();
}