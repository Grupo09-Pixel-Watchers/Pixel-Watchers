package entidades;

public class Computador {
        private int id;
        private String SO;
        private String fabricante;
        private Integer arquitetura;
        private Long memoria;
        private String dtInicializado;

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

        public String getFabricante() {
            return fabricante;
        }
        public void setFabricante(String fabricante) {
            this.fabricante = fabricante;
        }

        public Integer getArquitetura() {
            return arquitetura;
        }
        public void setArquitetura(Integer arquitetura) {
            this.arquitetura = arquitetura;
        }

        public String getDtInicializado() {
            return dtInicializado;
        }

        public void setDtInicializado(String dtInicializado) {
            this.dtInicializado = dtInicializado;
        }

        public Long getMemoria() {
            return memoria;
        }

        public void setMemoria(Long memoria) {
            this.memoria = memoria;
        }

}
