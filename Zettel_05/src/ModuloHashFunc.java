public class ModuloHashFunc  implements FixedRangeHashFunction<Object>{

    int maxSize;
    int minSize;

    public ModuloHashFunc(int maxSize, int minSize){
        this.maxSize = maxSize;
        this.minSize = minSize;
    }

    @Override
    public int hash(Object o) {
        return o.hashCode() % maxSize;
    }

    @Override
    public int minHashValue() {
        return minSize;
    }

    @Override
    public int maxHashValue() {
        return maxSize;
    }
}
