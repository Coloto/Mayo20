package com.softtek.Mayo20;

import com.softtek.Mayo20.modelo.Mascota;
import com.softtek.Mayo20.modelo.Propietario;
import com.softtek.Mayo20.repositorio.MascotaRepositorio;
import com.softtek.Mayo20.repositorio.MascotaRepositorioImpl;
import com.softtek.Mayo20.servicio.ExternalService;
import com.softtek.Mayo20.servicio.ExternalServiceImpl;
import com.softtek.Mayo20.servicio.MascotaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MascotaService03AssertJTest {
    @Test
    @DisplayName("register")
    void testRegistrarMascotaCorrectamente(){
        MascotaRepositorio mascotaRepository = new MascotaRepositorioImpl();
        ExternalService externalService = new ExternalServiceImpl();
        MascotaService mascotaService = new MascotaService(mascotaRepository, externalService);

        Propietario propietario = new Propietario("Dany", "Lima", "987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        Mascota registrada = mascotaService.registrarMascota(mascota);

        assertThat(registrada).isNotNull();
        assertThat(registrada.getNombre()).isEqualTo("Garfield");
        assertThat(registrada.getPropietario()).isNotNull();
        assertThat(registrada.getPropietario().getNombre()).isEqualTo("Dany");
        assertThat(registrada.getPropietario().getCiudad()).isEqualTo("Lima");
        assertThat(registrada.getPropietario().getTelefono()).isEqualTo("987654321");
        assertThat(registrada).isSameAs(mascota);
        assertThat(registrada.getId()).isPositive();


    }
}
