package com.smalaca.archetypes.party.organization;

import com.smalaca.archetypes.annotations.ArchetypeParty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ArchetypeParty.Organization
public class Organization {
    private final String name;
    private final List<Department> departments = new ArrayList<>();

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Department department) {
        departments.add(department);
    }

    public Collection<Department> getDepartments() {
        return List.copyOf(departments);
    }

    public Department getDepartment(String name) {
        return getDepartments().stream()
                .filter(unit -> unit.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
