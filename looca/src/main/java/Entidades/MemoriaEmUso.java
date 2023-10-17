package Entidades;

public class MemoriaEmUso {
        private String idMemoria;
        private Long memoriaUso;

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

}
