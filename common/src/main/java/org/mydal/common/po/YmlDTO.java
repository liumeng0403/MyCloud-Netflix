package org.mydal.common.po;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class YmlDTO {

    @Value("${peer.local}")
    private  String localVaue;

}
