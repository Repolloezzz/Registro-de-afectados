package com.example.proyectolab131.models;

import com.example.proyectolab131.enums.TipoMiembroFamilia;
import com.example.proyectolab131.persistence.ArchFamilia;
import com.example.proyectolab131.persistence.ArchPersona;
import com.example.proyectolab131.structures.LDNormal;


public class Familia extends LDNormal<MiembroF> {
    private int id;

    public Familia(int ci, int id) {
        super();
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(ci);
        if (miembro != null) {
            miembro.setFamiliaId(id);
            arch.editarUno(miembro.getCi(), miembro);
            agregarFin(new MiembroF(ci));
        }
        this.id = id;
    }

    public Familia(int ci, TipoMiembroFamilia tipo, int id) {
        super();
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(ci);
        if (miembro != null) {
            miembro.setFamiliaId(id);
            arch.editarUno(miembro.getCi(), miembro);
            agregarFin(new MiembroF(ci, tipo));
        }
        this.id = id;
    }

    public Familia(Persona familiar, int id) {
        super();
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(familiar.getCi());
        if (miembro != null) {
            miembro.setFamiliaId(id, true);
            arch.editarUno(miembro.getCi(), miembro);
            agregarFin(new MiembroF(miembro.getCi()));
        }
        this.id = id;
    }

    public Familia(Persona familiar, TipoMiembroFamilia tipo, int id) {
        super();
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(familiar.getCi());
        if (miembro != null) {
            miembro.setFamiliaId(id);
            arch.editarUno(miembro.getCi(), miembro);
            agregarFin(new MiembroF(miembro.getCi(), tipo));
        }
        this.id = id;
    }

    public void agregarFin(int ci) {
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(ci);
        if (miembro != null) {
            boolean sw = true;
            for (int i = 0; i < nroEle(); i++) {
                if (getK(i).getMiembroCI() == ci) {
                    sw = false;
                    break;
                }
            }
            if (sw) {
                agregarFin(new MiembroF(ci));
            } else {
                System.out.println("El miembro ya existe en la familia");
            }
        } else {
            System.out.println("La persona indicada no es persistente");
        }
    }

    public void agregarFin(int ci, TipoMiembroFamilia tipo) {
        ArchPersona arch = new ArchPersona();
        Persona miembro = arch.getPersona(ci);
        if (miembro != null) {
            boolean sw = true;
            for (int i = 0; i < nroEle(); i++) {
                if (getK(i).getMiembroCI() == ci) {
                    sw = false;
                    break;
                }
            }
            if (sw) {
                agregarFin(new MiembroF(ci, tipo));
            } else {
                System.out.println("El miembro ya existe en la familia");
            }
        } else {
            System.out.println("La persona indeicada no es persistente");
        }
    }

    public void agregarFin(Persona miembro) {
        agregarFin(miembro.getCi());
    }

    public void agregarFin(Persona miembro, TipoMiembroFamilia tipo) {
        agregarFin(miembro.getCi(), tipo);
    }

    public MiembroF eliminarMiembro(int ci) {
        MiembroF response = null;
        for (int i = 0; i < nroEle(); i++) {
            MiembroF miembro = getK(i);
            if (miembro.getMiembroCI() == ci) {
                response = removerK(i);
                break;
            }
        }
        nroEle--;
        return response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        ArchFamilia arch = new ArchFamilia();
        Familia fam = arch.getFamilia(id);
        if (fam == null) {
            this.id = id;
        } else {
            System.out.printf("El ID de la familia nueva ya existe");
        }
    }

    public void mostrar() {
        System.out.printf("ID: %s\n", id);
        for (int i = 0; i < nroEle(); i++) {
            MiembroF ele = getK(i);
            ele.mostrar();
        }
    }

    public LDNormal<Persona> getPersonas() {
        LDNormal<Persona> listResult = new LDNormal<>();
        for (int i = 0; i < nroEle(); i++) {
            MiembroF miembro = getK(i);
            Persona persona = miembro.getPersona();
            if (persona == null) {
                removerK(i);
                i--;
            } else {
                listResult.agregarFin(persona);
            }
        }
        return listResult;
    }
}
