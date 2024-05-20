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

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
        //ya registrado
        assertThatThrownBy(() -> mascotaService.registrarMascota(registrada));

        assertThat(registrada).isNotNull();
        assertThat(registrada.getNombre()).isEqualTo("Garfield");
        assertThat(registrada.getPropietario()).isNotNull();
        assertThat(registrada.getPropietario().getNombre()).isEqualTo("Dany");
        assertThat(registrada.getPropietario().getCiudad()).isEqualTo("Lima");
        assertThat(registrada.getPropietario().getTelefono()).isEqualTo("987654321");
        assertThat(registrada).isSameAs(mascota);
        assertThat(registrada.getId()).isPositive();

        //buscar por id
        Optional<Mascota> mascoton = mascotaService.buscarMascotaPorId(mascota.getId());

        //eliminar por id
        mascotaService.eliminarMascotaPorId(mascota.getId());

        //registrar sin nombre
        Mascota mascotillaSinNombre = new Mascota();
        mascotillaSinNombre.setNombre(null);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotillaSinNombre));

        //registrar sin propietario
        Mascota mascotillaSinPropietario = new Mascota();
        mascotillaSinPropietario.setNombre("Paco");
        mascotillaSinPropietario.setPropietario(null);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotillaSinPropietario));

        //registrar propietario sin telefono
        Propietario propietarioSinTelefono = new Propietario();
        propietarioSinTelefono.setNombre("Juan");
        propietarioSinTelefono.setCiudad("Madrid");
        propietarioSinTelefono.setTelefono(null);
        Mascota mascotillaSinTelefono = new Mascota();
        mascotillaSinTelefono.setNombre("Paquito");
        mascotillaSinTelefono.setPropietario(propietarioSinTelefono);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotillaSinTelefono));

        //registrar sin vacuna
        Mascota mascotillaSinVacunas  = new Mascota();
        mascotillaSinVacunas.setNombre("Paco sin vacuna");
        mascotillaSinVacunas.setPropietario(propietario);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotillaSinVacunas));

        //registrar sin municipio
        Mascota mascotillaSinMunicipio  = new Mascota();
        mascotillaSinMunicipio.setNombre("Paco no registrado");
        mascotillaSinMunicipio.setPropietario(propietario);
        assertThatThrownBy(() -> mascotaService.registrarMascota(mascotillaSinMunicipio));
    }
}
