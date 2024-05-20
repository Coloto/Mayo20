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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MascotaService02HamcrestTest {
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

        assertThat(registrada, is(notNullValue()));
        assertThat(registrada.getNombre(), is(equalTo("Garfield")));
        assertThat(registrada.getPropietario(), is(notNullValue()));
        assertThat(registrada.getPropietario().getNombre(), is(equalTo("Dany")));
        assertThat(registrada.getPropietario().getCiudad(), is(equalTo("Lima")));
        assertThat(registrada.getPropietario().getTelefono(), is(equalTo("987654321")));
        assertThat(registrada, is(sameInstance(mascota)));
        assertThat(registrada, is(notNullValue()));
    }
}
