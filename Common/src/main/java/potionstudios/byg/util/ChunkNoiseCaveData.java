package potionstudios.byg.util;


public interface ChunkNoiseCaveData {

    default void setCaveHeight(int x, int z, int y) {
        x = x & 15;
        z = z & 15;
        int index = x + z * 16;
        int caveHeight = getCaveHeights()[index];
        getCaveHeights()[index] = Math.min(caveHeight, y);
    }

    default int getCaveHeight(int x, int z) {
        x = x & 15;
        z = z & 15;
        return getCaveHeights()[x + z * 16];
   }

    int[] getCaveHeights();
}
