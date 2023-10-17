package Entidades;

public class Computador {
        private int id;
        private String SO;
        private Long memoriaTot;
        private String processador;
        private Long discoTotal;

        public int getId() {
            return this.id;
        }
        public void setId(int id) {
            this.id = id;
        }

        public String getSO() {
            return SO;
        }

        public void setSO(String So) {
            this.SO = So;
        }

        public Long getMemoriaTot() {
            return memoriaTot;
        }

        public void setMemoriaTot(Long memoria) {
            this.memoriaTot = memoria;
        }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public Long getDiscoTotal() {
        return discoTotal;
    }

    public void setDiscoTotal(Long discoTotal) {
        this.discoTotal = discoTotal;
    }

}
