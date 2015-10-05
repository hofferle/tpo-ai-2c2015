package modelo;

public class Efectivo extends MedioDePago {

    //~ MedioDePago Overrides --------------------------------------------------
    @Override
    public void guardar(Cliente cliente) {
        return;
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                getClass().getSimpleName()
        };
    }


    //~ Overrides --------------------------------------------------------------
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;
    }
}
