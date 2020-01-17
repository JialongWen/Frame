package com.wjl.frame.propagation;

import com.wjl.frame.annotation.MyTransactional;
import org.springframework.stereotype.Component;

@Component
public class SpreadControl {
        public boolean propageetionControl(MyTransactional myTransactional){
            switch (myTransactional.propagation()){
                case REQUIRED:
                    return false;
                    default:
                        return false;
            }
        }
}
