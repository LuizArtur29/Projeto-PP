package br.edu.ifpb.coffeeshop.drinkmanagement.model;

import java.util.List;

public interface Additional extends Item {
    List<Additional> getAdditionals();
    Additional getAdditional();
}