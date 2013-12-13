package de.webertise.simpleprpgui.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.webertise.simpleprpgui.exception.RoleNotFound;
import de.webertise.simpleprpgui.model.Role;
import de.webertise.simpleprpgui.repository.RoleRepository;
import de.webertise.simpleprpgui.service.IRoleService;

@Service
public class RoleService implements IRoleService {

    @Resource
    private RoleRepository RoleRepository;

    @Override
    @Transactional
    public Role create(Role Role) {
        Role createdRole = Role;
        return RoleRepository.save(createdRole);
    }

    @Override
    @Transactional
    public Role findById(int id) {
        return RoleRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = RoleNotFound.class)
    public Role delete(int id) throws RoleNotFound {
        Role deletedRole = RoleRepository.findOne(id);

        if (deletedRole == null)
            throw new RoleNotFound();

        RoleRepository.delete(deletedRole);
        return deletedRole;
    }

    @Override
    @Transactional
    public List<Role> findAll() {
        return RoleRepository.findAll();
    }

    @Override
    @Transactional(rollbackFor = RoleNotFound.class)
    public Role update(Role Role) throws RoleNotFound {
        // TODO: int => long
        Role updatedRole = RoleRepository.findOne((int) Role.getId());

        if (updatedRole == null)
            throw new RoleNotFound();

        // TODO: alle Felder updaten
        updatedRole.setName(Role.getName());
        return updatedRole;
    }

}
