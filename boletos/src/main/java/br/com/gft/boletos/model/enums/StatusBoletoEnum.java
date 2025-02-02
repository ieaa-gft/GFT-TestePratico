package br.com.gft.boletos.model.enums;

public enum StatusBoletoEnum {

	PENDENTE(0), 
	PAGO(1), 
	VENCIDO(2);

	public final int statusCode;

	private StatusBoletoEnum(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return this.statusCode;
	}

	public static StatusBoletoEnum buscarStatusPorCodigo(Integer codigo) {
		for (StatusBoletoEnum status : StatusBoletoEnum.values()) {
			if (status.getStatusCode() == codigo) {
				return status;
			}
		}
		throw new IllegalArgumentException("Invalid Status Code");
	}

}