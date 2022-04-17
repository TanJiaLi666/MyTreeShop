package com.tulingxueyuan.mall.modules.ums.dto.domain;

import com.tulingxueyuan.mall.modules.ums.model.UmsResource;
import com.tulingxueyuan.mall.modules.ums.model.UmsRole;
import lombok.Data;

import java.util.List;
@Data
public class ResourceVO extends UmsResource {
    List<UmsRole> roleList;
}
