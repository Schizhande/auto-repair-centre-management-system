package com.schizhande.usermanagementsystem.service.impl;

import com.schizhande.usermanagementsystem.dao.jpa.BaseRepository;
import com.schizhande.usermanagementsystem.dao.jpa.CustomSpecificationTemplateImplBuilder;
import com.schizhande.usermanagementsystem.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Objects;

public abstract class BaseServiceImpl<T, C, U>  implements BaseService<T, C, U> {

    protected final BaseRepository<T> repository;

    public BaseServiceImpl(BaseRepository<T> repository) {
        this.repository = repository;
    }

    public T findById(Long id) {
        return this.repository.findById(id).orElseThrow(() -> {
            return new NoSuchElementException("Record not found for the given id : " + id);
        });
    }

    @Transactional
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public Page<T> findAll(Pageable pageable, String searchQuery) {
        if (Objects.isNull(searchQuery)) {
            return this.repository.findAll(pageable);
        } else {
            Specification<T> spec = (new CustomSpecificationTemplateImplBuilder()).buildSpecification(searchQuery);
            return this.repository.findAll(spec, pageable);
        }
    }

    public Collection<T> findAll() {
        return this.repository.findAll();
    }

}
