public enum StatusResponse {
    SUCCESS  ("Respuesta satisfactoria."),
    ERROR  ("Respuesta erronea.");

    private String status;
    StatusResponse(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
