package com.softtek.Mayo20.repositorio;

import com.softtek.Mayo20.modelo.Mascota;

import java.util.Optional;

public interface MascotaRepositorio {
    Mascota guardar(Mascota mascota);
    Optional<Mascota> findById(Integer id);
    void deleteById(Integer id);
}
