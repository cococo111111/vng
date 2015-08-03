<?php
/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
include_once $GLOBALS['THRIFT_ROOT'].'/Thrift.php';

class contentfilter_ContentFilterData {
  static $_TSPEC;

  public $systemId = null;
  public $appId = null;
  public $objectId = null;
  public $contentId = null;
  public $ownerId = null;
  public $toUserId = null;
  public $time = null;
  public $content = null;
  public $parentId = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'systemId',
          'type' => TType::I16,
          ),
        2 => array(
          'var' => 'appId',
          'type' => TType::I16,
          ),
        3 => array(
          'var' => 'objectId',
          'type' => TType::I64,
          ),
        4 => array(
          'var' => 'contentId',
          'type' => TType::I64,
          ),
        5 => array(
          'var' => 'ownerId',
          'type' => TType::I32,
          ),
        6 => array(
          'var' => 'toUserId',
          'type' => TType::I32,
          ),
        7 => array(
          'var' => 'time',
          'type' => TType::I32,
          ),
        8 => array(
          'var' => 'content',
          'type' => TType::STRING,
          ),
        9 => array(
          'var' => 'parentId',
          'type' => TType::I64,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['systemId'])) {
        $this->systemId = $vals['systemId'];
      }
      if (isset($vals['appId'])) {
        $this->appId = $vals['appId'];
      }
      if (isset($vals['objectId'])) {
        $this->objectId = $vals['objectId'];
      }
      if (isset($vals['contentId'])) {
        $this->contentId = $vals['contentId'];
      }
      if (isset($vals['ownerId'])) {
        $this->ownerId = $vals['ownerId'];
      }
      if (isset($vals['toUserId'])) {
        $this->toUserId = $vals['toUserId'];
      }
      if (isset($vals['time'])) {
        $this->time = $vals['time'];
      }
      if (isset($vals['content'])) {
        $this->content = $vals['content'];
      }
      if (isset($vals['parentId'])) {
        $this->parentId = $vals['parentId'];
      }
    }
  }

  public function getName() {
    return 'ContentFilterData';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I16) {
            $xfer += $input->readI16($this->systemId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I16) {
            $xfer += $input->readI16($this->appId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->objectId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 4:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->contentId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 5:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->ownerId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 6:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->toUserId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 7:
          if ($ftype == TType::I32) {
            $xfer += $input->readI32($this->time);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 8:
          if ($ftype == TType::STRING) {
            $xfer += $input->readString($this->content);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 9:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->parentId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('ContentFilterData');
    if ($this->systemId !== null) {
      $xfer += $output->writeFieldBegin('systemId', TType::I16, 1);
      $xfer += $output->writeI16($this->systemId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->appId !== null) {
      $xfer += $output->writeFieldBegin('appId', TType::I16, 2);
      $xfer += $output->writeI16($this->appId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->objectId !== null) {
      $xfer += $output->writeFieldBegin('objectId', TType::I64, 3);
      $xfer += $output->writeI64($this->objectId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->contentId !== null) {
      $xfer += $output->writeFieldBegin('contentId', TType::I64, 4);
      $xfer += $output->writeI64($this->contentId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->ownerId !== null) {
      $xfer += $output->writeFieldBegin('ownerId', TType::I32, 5);
      $xfer += $output->writeI32($this->ownerId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->toUserId !== null) {
      $xfer += $output->writeFieldBegin('toUserId', TType::I32, 6);
      $xfer += $output->writeI32($this->toUserId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->time !== null) {
      $xfer += $output->writeFieldBegin('time', TType::I32, 7);
      $xfer += $output->writeI32($this->time);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->content !== null) {
      $xfer += $output->writeFieldBegin('content', TType::STRING, 8);
      $xfer += $output->writeString($this->content);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->parentId !== null) {
      $xfer += $output->writeFieldBegin('parentId', TType::I64, 9);
      $xfer += $output->writeI64($this->parentId);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class contentfilter_ObjectContent {
  static $_TSPEC;

  public $objectId = null;
  public $contentId = null;
  public $parentId = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'objectId',
          'type' => TType::I64,
          ),
        2 => array(
          'var' => 'contentId',
          'type' => TType::I64,
          ),
        3 => array(
          'var' => 'parentId',
          'type' => TType::I64,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['objectId'])) {
        $this->objectId = $vals['objectId'];
      }
      if (isset($vals['contentId'])) {
        $this->contentId = $vals['contentId'];
      }
      if (isset($vals['parentId'])) {
        $this->parentId = $vals['parentId'];
      }
    }
  }

  public function getName() {
    return 'ObjectContent';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->objectId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->contentId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 3:
          if ($ftype == TType::I64) {
            $xfer += $input->readI64($this->parentId);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('ObjectContent');
    if ($this->objectId !== null) {
      $xfer += $output->writeFieldBegin('objectId', TType::I64, 1);
      $xfer += $output->writeI64($this->objectId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->contentId !== null) {
      $xfer += $output->writeFieldBegin('contentId', TType::I64, 2);
      $xfer += $output->writeI64($this->contentId);
      $xfer += $output->writeFieldEnd();
    }
    if ($this->parentId !== null) {
      $xfer += $output->writeFieldBegin('parentId', TType::I64, 3);
      $xfer += $output->writeI64($this->parentId);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

class contentfilter_FilterResult {
  static $_TSPEC;

  public $resultList = null;
  public $totalRecord = null;

  public function __construct($vals=null) {
    if (!isset(self::$_TSPEC)) {
      self::$_TSPEC = array(
        1 => array(
          'var' => 'resultList',
          'type' => TType::LST,
          'etype' => TType::STRUCT,
          'elem' => array(
            'type' => TType::STRUCT,
            'class' => 'contentfilter_ContentFilterData',
            ),
          ),
        2 => array(
          'var' => 'totalRecord',
          'type' => TType::I16,
          ),
        );
    }
    if (is_array($vals)) {
      if (isset($vals['resultList'])) {
        $this->resultList = $vals['resultList'];
      }
      if (isset($vals['totalRecord'])) {
        $this->totalRecord = $vals['totalRecord'];
      }
    }
  }

  public function getName() {
    return 'FilterResult';
  }

  public function read($input)
  {
    $xfer = 0;
    $fname = null;
    $ftype = 0;
    $fid = 0;
    $xfer += $input->readStructBegin($fname);
    while (true)
    {
      $xfer += $input->readFieldBegin($fname, $ftype, $fid);
      if ($ftype == TType::STOP) {
        break;
      }
      switch ($fid)
      {
        case 1:
          if ($ftype == TType::LST) {
            $this->resultList = array();
            $_size0 = 0;
            $_etype3 = 0;
            $xfer += $input->readListBegin($_etype3, $_size0);
            for ($_i4 = 0; $_i4 < $_size0; ++$_i4)
            {
              $elem5 = null;
              $elem5 = new contentfilter_ContentFilterData();
              $xfer += $elem5->read($input);
              $this->resultList []= $elem5;
            }
            $xfer += $input->readListEnd();
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        case 2:
          if ($ftype == TType::I16) {
            $xfer += $input->readI16($this->totalRecord);
          } else {
            $xfer += $input->skip($ftype);
          }
          break;
        default:
          $xfer += $input->skip($ftype);
          break;
      }
      $xfer += $input->readFieldEnd();
    }
    $xfer += $input->readStructEnd();
    return $xfer;
  }

  public function write($output) {
    $xfer = 0;
    $xfer += $output->writeStructBegin('FilterResult');
    if ($this->resultList !== null) {
      if (!is_array($this->resultList)) {
        throw new TProtocolException('Bad type in structure.', TProtocolException::INVALID_DATA);
      }
      $xfer += $output->writeFieldBegin('resultList', TType::LST, 1);
      {
        $output->writeListBegin(TType::STRUCT, count($this->resultList));
        {
          foreach ($this->resultList as $iter6)
          {
            $xfer += $iter6->write($output);
          }
        }
        $output->writeListEnd();
      }
      $xfer += $output->writeFieldEnd();
    }
    if ($this->totalRecord !== null) {
      $xfer += $output->writeFieldBegin('totalRecord', TType::I16, 2);
      $xfer += $output->writeI16($this->totalRecord);
      $xfer += $output->writeFieldEnd();
    }
    $xfer += $output->writeFieldStop();
    $xfer += $output->writeStructEnd();
    return $xfer;
  }

}

?>