rm ./gen-java/vng/zingme/payment/thrift/*.java
rm ./gen-php/*/*.php

thrift --gen java zingme_payment_type.thrift
thrift --gen java zingme_payment_service.thrift

thrift --gen php zingme_payment_type.thrift
thrift --gen php zingme_payment_service.thrift
