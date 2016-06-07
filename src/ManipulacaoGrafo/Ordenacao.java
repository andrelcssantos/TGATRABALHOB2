/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManipulacaoGrafo;

import ElementosGrafo.Aresta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author GUILHERME
 */
public class Ordenacao {

    public List<Aresta> quickSort(List<Aresta> a) {
        Aresta[] aresta = new Aresta[a.size()];
        Aresta[] arestaAUX = new Aresta[a.size()];
        int inicio = 0;
        int fim = 0;
        for(Aresta A : a) {
            aresta[fim] = A;
            System.out.print(aresta[fim].getRotulo());
            fim++;
        }
        aresta = quickSort(aresta, inicio, fim-1);
        a.clear();
        
        while(inicio < aresta.length) {
            System.out.print(aresta[inicio].getRotulo());
            a.add(aresta[inicio]);
            inicio++;
        }
        return a;
    }
    
    public Aresta[] quickSort(Aresta[] a, int inicio, int fim) {
        if (inicio < fim) {
            int posicaoPivo = separar(a, inicio, fim);
            quickSort(a, inicio, posicaoPivo - 1);
            quickSort(a, posicaoPivo + 1, fim);
        }
        return a;
    }

    private static int separar(Aresta[] a, int inicio, int fim) {
        Aresta pivo = a[inicio];
        int i = inicio + 1, f = fim;
        while (i <= f) {
            if (Integer.parseInt(a[i].getRotulo().trim()) <= Integer.parseInt(pivo.getRotulo().trim())) {
                i++;
            } else if (Integer.parseInt(pivo.getRotulo().trim()) < Integer.parseInt(a[f].getRotulo().trim())) {
                f--;
            } else {
                Aresta troca = a[i];
                a[i] = a[f];
                a[f] = troca;
                i++;
                f--;
            }
        }
        a[inicio] = a[f];
        a[f] = pivo;
        return f;
    }
}
