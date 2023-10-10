package entidades;

public class MemoriaEmUso {
        private String idMemoria;
        private Long memoriaUso;
        private Double processadorUso;



        public String getIdMemoria() {
            return this.idMemoria;
        }
        public void setIdMemoria(String id) {
            this.idMemoria = id;
        }

        public Long getMemoriaUso() {
            return memoriaUso;
        }

        public void setMemoriaUso(Long memoria) {
            this.memoriaUso = memoria;
        }

        public Double getProcessadorUso() {
            return processadorUso;
        }

        public void setProcessadorUso(Double processadorUso) {
            this.processadorUso = processadorUso;
        }

}
