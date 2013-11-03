//Prototipos.js
//Version 1.0
//Made By Alejandro Fontes
//Extension de clases String, Array, int entre otras

function inicializarPrototipos() {

	String.prototype.toPriceEntero = function () {

		// Este metodo convierte el string a un numero de la forma 123.321,50
		var v;
		if (/^\d+(,\d+)$/.test(this))
			v = this.replace(/,/, '.');
		else if (/^\d+((,\d{3})*(\.\d+)?)?$/.test(this))
			v = this.replace(/,/g, "");
		else if (/^\d+((.\d{3})*(,\d+)?)?$/.test(this))
			v = this.replace(/\./g, "").replace(/,/, ".");
		var x = parseFloat(v).toFixed(2).toString().split("."),
		x1 = x[0],
		x2 = ((x.length == 2) ? "," + x[1] : ".00"),
		exp = /^([0-9]+)(\d{3})/;
		while (exp.test(x1))
			x1 = x1.replace(exp, "$1" + "." + "$2");
		return x1 + x2;
	}

	String.prototype.toPriceSinComa = function () {

		// Este metodo convierte el string a un numero de la forma 123.321
		var v;
		if (/^\d+(,\d+)$/.test(this))
			v = this.replace(/,/, '.');
		else if (/^\d+((,\d{3})*(\.\d+)?)?$/.test(this))
			v = this.replace(/,/g, "");
		else if (/^\d+((.\d{3})*(,\d+)?)?$/.test(this))
			v = this.replace(/\./g, "").replace(/,/, ".");
		//var x = parseFloat(v).toFixed(2).toString().split("."),
		var x = parseFloat(v).toFixed(0).toString(),
			//x1 = x[0],
			x1 = x,
			exp = /^([0-9]+)(\d{3})/;
		while (exp.test(x1))
			x1 = x1.replace(exp, "$1" + "." + "$2");
		return x1;
	}
	
	String.prototype.validarFecha = function( ){
		//Validar el formato de la fecha, y que sea una fecha valida
		
		function daysInFebruary (year){
			return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
		}
		
		var formato = this.split('/');

		var dia = parseInt(formato[0]);	
		var mes = parseInt(formato[1]);
		var anio = parseInt(formato[2]);
		
		var diasEnMes = [31,daysInFebruary(anio),31,30,31,30,31,31,30,31,30,31];
		
		mes--;
		
		if (1900 < anio && anio < 2100){
			if (0 <= mes && mes <= 11){
				if (0 < dia && dia <= diasEnMes[mes]){
					return true;
				}
			}
		}
		return false;
	}

	String.prototype.isNumeric = function( ){

		if (this == undefined)
			return false

		var validChars = '0123456789.';

		for(var i = 0; i < this.length; i++) {
			if(validChars.indexOf(this.charAt(i)) == -1)
				return false;
		}
		return true;
	}
	
	String.prototype.esSimboloMatematico = function( ){

		if (this == undefined)
			return false

		var validChars = '()+-*/';

		for(var i = 0; i < this.length; i++) {
			var caracter = this.charAt(i);
			if(validChars.indexOf(caracter) != -1)
				return true;
		}
		return false;
	}
	
	String.prototype.reemplazarComas = function( ) {
		var resultado = this;
		if (resultado != "") {
			while (resultado.search(',') != -1) {
				resultado = resultado.replace(",",".");
			}
		}
		return resultado;
	}
	
	String.prototype.quitarTildesYOtros = function() { 
	
		var from = "ÃÀÁÄÂÈÉËÊÌÍÏÎÒÓÖÔÙÚÜÛãàáäâèéëêìíïîòóöôùúüûÇç",
			to   = "AAAAAEEEEIIIIOOOOUUUUaaaaaeeeeiiiioooouuuucc",
			mapping = {};
	 
		for(var i = 0, j = from.length; i < j; i++ )
			mapping[ from.charAt( i ) ] = to.charAt( i );
		
		var str = this;
		var ret = [];
		for( var i = 0, j = str.length; i < j; i++ ) {
			var c = str.charAt( i );
			if( mapping.hasOwnProperty( str.charAt( i ) ) )
				ret.push( mapping[ c ] );
			else
				ret.push( c );
		}
		return ret.join( '' );
	} 

	String.prototype.validarMail = function(){
	//Regresa TRUE si el mail esta bien formado, FALSE en caso contrario	
		var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
		if( !emailReg.test(this) )
			return false;
		else
			return true;
	}

	Array.prototype.moverElemento = function(index, nuevoIndex){

		// Este metodo mueve el elemento que esta en la posicion index
		// a la posicion nuevoIndex, y conserva la posicion de los demas elementos

		// Por ejemplo:
		// myarray = {0,1,2,3,4,5}
		// myarray.moverElemento(5, 0); Mueve el elemento de la posicion 5 a la 0
		// conservando la posicion de los demas elementos
		// myarray = {5,0,1,2,3,4}

		var copiaElem = this.splice(index,1);
		this.splice(nuevoIndex,0,copiaElem[0]);

		return true;
	};
	
	Array.prototype.clear = function() {
	
		while (this.length > 0) {
			this.pop();
		}
	};
	
	Array.prototype.contains = function(otro) {
		for(var i =0; i< this.length; i++){
			if(this[i] == otro){
				return true;
			}
		}
		return false;
	};
}