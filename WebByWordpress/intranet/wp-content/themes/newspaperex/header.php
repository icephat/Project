<?php

/**
 * The header for our theme.
 *
 * This is the template that displays all of the <head> section and everything up until <div id="content">
 *
 * @package Newspaperex
 */
?>
<!DOCTYPE html>
<html <?php language_attributes(); ?>>

<head>

  <meta charset="<?php bloginfo('charset'); ?>">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- <link rel="stylesheet" href="./wp-content/themes/newspaperex/css/bootstrap/font-awesome.min.css">
  <link rel="stylesheet" href="./wp-content/themes/newspaperex/css/bootstrap/bootstrap.min.css"> -->
  <link rel="stylesheet" href="./wp-content/themes/newspaperex/css/bootstrap.css">
  <link rel="stylesheet" href="./wp-content/themes/newspaperex/css/font-awesome.min.css">


  <?php wp_head(); ?>


</head>

<body <?php body_class(); ?>>
  <?php wp_body_open(); ?>
  <div id="page" class="site">
    <a class="skip-link screen-reader-text" href="#content">
      <?php _e('Skip to content', 'newspaperex'); ?></a>
    <div class="wrapper" id="custom-background-css">
      <header class="mg-headwidget">
        <!--==================== TOP BAR ====================-->
        <?php do_action('newspaperex_action_header_section');  ?>
        <div class="clearfix"></div>
        <?php $background_image = get_theme_support('custom-header', 'default-image');
        if (has_header_image()) {
          $background_image = get_header_image();
        } ?>
        <div class="mg-nav-widget-area-back" style='background-image: url("<?php echo esc_url($background_image); ?>" );'>
          <?php $remove_header_image_overlay = get_theme_mod('remove_header_image_overlay', false); ?>
          <div class="overlay">
            <div class="inner" <?php if ($remove_header_image_overlay == false) {
                                  $newsup_header_overlay_color = get_theme_mod('newsup_header_overlay_color'); ?> style="background-color:<?php echo esc_attr($newsup_header_overlay_color); ?>;" <?php } ?>>
              <div class="container-fluid">
                <div class="mg-nav-widget-area">
                  <div class="row align-items-center">
                    <?php $newsup_right_banner_advertisement = newsup_get_option('banner_right_advertisement_section');
                    $newsup_center_logo_title = get_theme_mod('newsup_center_logo_title', false);
                    do_action('newspaperex_action_right_banner_advertisement');
                    if ($newsup_center_logo_title == false) { ?>
                      <div class="col-md-4 col-sm-4">
                      <?php } else { ?>
                        <div class="col-12 text-center mt-3">
                        <?php }
                      if ($newsup_right_banner_advertisement) { ?>
                          <div class="navbar-header text-center">
                          <?php } else { ?> <div class="navbar-header"><?php }
                                                                      the_custom_logo();
                                                                      if (display_header_text()) : ?>
                              <div class="site-branding-text ">
                                <?php if (is_front_page() || is_home()) { ?>
                                  <h1 class="site-title">
                                    <div class="row">
                                      <img src="./wp-content/img/LOGO3.png" width="100" height="auto" style="margin-right: 30px;">
                                      <a href="<?php echo esc_url(home_url('/')); ?>" rel="home" style="text-decoration: none;">
                                        <?php echo esc_html(get_bloginfo('name')); ?>

                                      </a>

                                    </div>

                                  <?php } else { ?>
                                    <h1 class="site-title">
                                      <div class="row">
                                        <img src="../.././wp-content/img/LOGO3.png" width="100" height="auto" style="margin-right: 30px;">
                                        <a href="<?php echo esc_url(home_url('/')); ?>" rel="home" style="text-decoration: none;">
                                          <?php echo esc_html(get_bloginfo('name')); ?>

                                        </a>

                                      </div>
                                    <?php } ?>
                                    <p class="site-description"><?php echo esc_html(get_bloginfo('description')); ?></p>
                              </div>
                            <?php endif; ?>
                            </div>
                          </div>
                          <?php do_action('newspaperex_action_banner_advertisement'); ?>
                        </div>
                      </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="mg-menu-full">
              <nav class="navbar navbar-expand-lg navbar-wp">
                <div class="container-fluid flex-row" style="background-color: #192655;">
                  <!-- Right nav -->
                  <div class="m-header pl-3 ml-auto my-2 my-lg-0 position-relative align-items-center">
                    <?php $home_url = home_url(); ?>
                    <a class="mobilehomebtn" href="<?php echo esc_url($home_url); ?>"><span class="fa fa-home"></span></a>

                    <?php $search_enable = get_theme_mod('header_search_enable', 'true');
                    $subsc_enable = get_theme_mod('header_subsc_enable', 'true');
                    $subsc_target = get_theme_mod('newsup_subsc_link_target', 'true');
                    $subsc_link = get_theme_mod('newsup_subsc_link', '#');
                    if ($search_enable == true) { ?>
                      <!-- Search -->
                      <div class="dropdown ml-auto show mg-search-box pr-3">
                        <a class="dropdown-toggle msearch ml-auto" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="fa fa-search"></i>
                        </a>
                        <div class="dropdown-menu searchinner" aria-labelledby="dropdownMenuLink">
                          <?php get_search_form(); ?>
                        </div>
                      </div>
                      <!-- /Search -->
                    <?php }
                    if ($subsc_enable == true) { ?>
                      <!-- Subscribe Button -->
                      <a href="<?php echo esc_url($subsc_link); ?>" <?php if ($subsc_target) { ?> target="_blank" <?php } ?> class="btn-bell btn-theme mx-2"><i class="fa fa-bell"></i></a>
                      <!-- /Subscribe Button -->
                    <?php } ?>
                    <!-- navbar-toggle -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-wp" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="<?php esc_attr_e('Toggle navigation', 'newspaperex'); ?>">
                      <i class="fa fa-bars"></i>
                    </button>
                    <!-- /navbar-toggle -->
                  </div>
                  <!-- /Right nav -->
                  <style>
                    @keyframes arrowBlink {
                      0% {
                        opacity: 1;
                      }

                      50% {
                        opacity: 0.5;
                      }

                      100% {
                        opacity: 1;
                      }
                    }

                    .dropbtn {
                      background-color: #192655;
                      color: white;
                      font-size: 16px;
                      border: none;
                      cursor: pointer;
                    }


                    .dropdown {
                      position: relative;
                      display: inline-block;
                    }

                    .dropdown-content {
                      display: none;
                      position: absolute;
                      right: 0;
                      background-color: #192655;
                      min-width: 160px;
                      z-index: 1;
                    }

                    .dropdown-content a {
                      color: black;
                      padding: 12px 16px;
                      text-decoration: none;
                      display: block;
                    }

                    .dropdown-content a:hover {
                      background-color: #192655;
                    }

                    .dropdown:hover .dropdown-content {
                      display: block;
                    }

                    .dropdown:hover .dropbtn {
                      background-color: #192655;
                    }

                    .dropright,
                    .dropright2,
                    .dropright3,
                    .dropright4,
                    .dropright5,
                    .dropright6,
                    .dropright7 {
                      left: 100%;
                      top: 0%;
                      margin-left: 0.125rem;
                    }

                    /* .center {
                      display: flex;
                      justify-items: center;
                      text-align: center;
                    } */

                    .nav-link a:hover {
                      color: black;
                      right: 0;
                      background-color: #FBF3D5;
                      min-width: 160px;
                      z-index: 1;
                    }

                    .nav-link a {
                      color: white;
                    }

                    .drop-wrapper {
                      position: relative;
                      background-color: #192655;
                      display: inline-block;
                    }

                    .drop-menu {
                      display: none;
                      position: absolute;
                      right: 0;
                      background-color: #192655;
                      min-width: 160px;
                      z-index: 1;
                    }


                    .drop-menu.active {
                      display: block;
                    }

                    .fa-arrow-circle-right {
                      font-size: x-small;
                      /* ปรับขนาดไอคอนให้เล็กลง */
                    }

                    .drop-wrapper:hover .drop-menu {
                      display: block;
                    }

                    .drop-wrapper .btn i {
                      display: inline-block;
                      transition: transform 0.3s ease;
                      /* เพิ่ม transition เพื่อให้ลูกศรมีการเคลื่อนไหวเมื่อคลิก */
                      font-size: smaller;
                      /* กำหนดขนาดเริ่มต้นของลูกศร */
                    }

                    .drop-wrapper:hover .btn i {
                      animation: arrowBlink 0.2s infinite;
                      /* เพิ่มการกระพริบ */
                      font-size: medium;
                      /* เพิ่มขนาดของลูกศรเมื่อโฮเวอร์ */
                    }
                  </style>


                  <div class="collapse navbar-collapse" id="navbar-wp">
                    <div class="d-md-block">
                      <ul class="nav navbar-nav" data-smartmenus-id="16970085695541528">
                        <li class="nav-item menu-item active"><a class="nav-link " href="http://app.urcrice.com:81/intranet" title="Home">Home</a></li>
                        <li class="nav-item menu-item ">
                          <div>
                            <div class="nav-link dropdown" style="float:left;">
                              <!-- <button class="btn" style=" box-shadow: none;">แผนกบุคคล</button> -->
                              <a class="btn" style="text-align: left;">แผนกบุคคล</a>

                              <div class="dropdown-content" style="left:0;">


                                <div class="drop-wrapper">

                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright1" onclick="toggleDropdown(event)">1.แบบฟอร์ม บันทึกการประชุม ตอนเช้า 10 นาที <i class=" fa fa-arrow-circle-right" aria-hidden="true" data-target="#dropright1"></i></a>
                                  <div class="drop-menu dropright " id="dropright1">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/1/FMeeting001.pdf" title="Home">1.แบบฟอร์ม บันทึกการประชุม ตอนเช้า 10 นาที</a>
                                  </div>
                                </div>

                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright2" onclick="toggleDropdown(event)">2.สัญญาค้ำประกัน <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright2 " id="dropright2">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/2.สัญญาค้ำประกัน/สัญญาค้ำประกันบุคคลเข้าทำงานกับบริษัท ยูนิเวอร์แซล ไรซ์.docx" title="Home" download>สัญญาค้ำประกันบุคคลเข้าทำงานกับบริษัท</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/2.สัญญาค้ำประกัน/หลักการค้ำประกันบุคคลเข้าทำงาน.docx" title="Home" download>หลักการค้ำประกันบุคคลเข้าทำงาน</a>
                                  </div>
                                </div>

                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright3" onclick="toggleDropdown(event)">3.สัญญาจ้างแบบมีกำหนดระยะเวลา <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright3 " id="dropright3">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาจ้างแบบมีกำหนดระยะเวลาการจ้าง - ยกเลิก.docx" title="Home" download>สัญญาจ้างแบบมีกำหนดระยะเวลาการจ้าง - ยกเลิก</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาว่าจ้างแบบมีกำหนดระยะเวลา (ต้นฉบับ)...docx" title="Home" download>สัญญาว่าจ้างแบบมีกำหนดระยะเวลา (ต้นฉบับ)</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาว่าจ้างแบบมีกำหนดระยะเวลา (ผจก.IT).docx" title="Home" download>สัญญาว่าจ้างแบบมีกำหนดระยะเวลา (ผจก.IT)</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาว่าจ้างแบบมีกำหนดระยะเวลา สุนิยม QC.docx" title="Home" download>สัญญาว่าจ้างแบบมีกำหนดระยะเวลา สุนิยม QC</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาว่าจ้างแบบมีกำหนดระยะเวลา อ.ออมทรัพย์.docx" title="Home" download>สัญญาว่าจ้างแบบมีกำหนดระยะเวลา อ.ออมทรัพย์</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/3.สัญญาจ้างแบบมีกำหนดระยะเวลา/สัญญาว่าจ้างแบบมีกำหนดระยะเวลา อมรศักดิ์.docx" title="Home" download>สัญญาว่าจ้างแบบมีกำหนดระยะเวลา อมรศักดิ์</a>
                                  </div>
                                </div>

                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright4" onclick="toggleDropdown(event)">4.สัญญาจ้างพนักงานต่างด้าว2566 <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright4 " id="dropright4">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/4.สัญญาจ้างพนักงานต่างด้าว2566/PDFFสัญญาว่าจ้างไทยพม่าแบบมีกำหนดระยะเวลา.66.pdf" title="Home" download>สัญญาค้ำประกันบุคคลเข้าทำงานกับบริษัท</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/4.สัญญาจ้างพนักงานต่างด้าว2566/PDFสัญญาว่าจ้างไทยพม่าแบบมีกำหนดระยะเวลา.pdf" title="Home" download>หลักการค้ำประกันบุคคลเข้าทำงาน</a>
                                  </div>
                                </div>

                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright5" onclick="toggleDropdown(event)">5.เอกสารเกี่ยวข้องกับ การฝึกอบรม <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright5 " id="dropright5">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/5.เอกสารเกี่ยวข้องกับ การฝึกอบรม/1.แบบฟอร์มขออนุมัติ การฝึกอบรม/ใบขออนุมัติฝึกอบรม.pdf" title="Home" download>ใบขออนุมัติฝึกอบรม</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/5.เอกสารเกี่ยวข้องกับ การฝึกอบรม/2.แบบฟอร์มรายงานผลการเข้าอบรมสัมมนา F-TN-005 Re05/แบบฟอร์มรายงานผลการเข้าอบรมสัมมนา F-TN-005 Re05.pdf" title="Home" download>แบบฟอร์มรายงานผลการเข้าอบรมสัมมนา F-TN-005 Re05</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/5.เอกสารเกี่ยวข้องกับ การฝึกอบรม/3.ใบประเมินผลหลังการฝึกอบรม F-TN-013 Re00/ใบประเมินผลหลังการฝึกอบรม F-TN-013 Re00.pdf" title="Home" download>ใบประเมินผลหลังการฝึกอบรม F-TN-013 Re00</a>
                                  </div>
                                </div>

                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright6" onclick="toggleDropdown(event)">6.อื่นๆ <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright6 " id="dropright6" style="height: 500px; overflow-y: auto; ">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/(ฉบับแก้ไข) หนังสือต่อท้ายสัญญาจ้างงานรายเดือน update.docx" title="Home" download>(ฉบับแก้ไข) หนังสือต่อท้ายสัญญาจ้างงานรายเดือน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/(ฉบับแก้ไข) หนังสือต่อท้ายสัญญาจ้างงานรายวัน  update.docx" title="Home" download>(ฉบับแก้ไข) หนังสือต่อท้ายสัญญาจ้างงานรายวัน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F HR011 Rev.3 ประเมินผลการสัมภาษณ์ผู้สมัครงาน.pdf" title="Home" download>F HR011 Rev.3 ประเมินผลการสัมภาษณ์ผู้สมัครงาน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-AM-003 Rev.03 ใบนำสิ่งของออกนอกบริษัท (1).pdf" title="Home" download>F-AM-003 Rev.03 ใบนำสิ่งของออกนอกบริษัท (1)</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-002  Rev.03 ใบอนุญาตออกนอกโรงงาน2.pdf" title="Home" download>F-HR-002 Rev.03 ใบอนุญาตออกนอกโรงงาน2</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-002 Rev.03 ใบขออนุญาติออกนอกโรงงาน.pdf" title="Home" download>F-HR-002 Rev.03 ใบขออนุญาติออกนอกโรงงาน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-030  ยืมอัตรากำลังคน.pdf" title="Home" download>F-HR-030 ยืมอัตรากำลังคน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-030 REV.02 แบบฟอร์มขอยืมอัตรากำลังคน.pdf" title="Home" download>F-HR-030 REV.02 แบบฟอร์มขอยืมอัตรากำลังคน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-031 Rev.03 ใบขออนุญาติทำงานต่างจังหวัด.pdf" title="Home" download>F-HR-031 Rev.03 ใบขออนุญาติทำงานต่างจังหวัด</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-037 Rev.01 ใบขออนุญาตทำงานล่วงเวลา.pdf" title="Home" download>F-HR-037 Rev.01 ใบขออนุญาตทำงานล่วงเวลา</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/FORMDAR  Rev.07.pdf" title="Home" download>FORMDAR Rev.07</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-004 Rev.02 ใบจองคิวรถ.pdf" title="Home" download>F-PN-004 Rev.02 ใบจองคิวรถ</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-008 Rev.05_สัญญาจ้างงาน ยูนิเวอร์แซล ไรซ์.docx" title="Home" download>F-PN-008 Rev.05_สัญญาจ้างงาน ยูนิเวอร์แซล ไรซ์</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-HR-007 Rev.02   ใบสมัครงานรายเดือน.xls" title="Home" download>F-HR-007 Rev.02 ใบสมัครงานรายเดือน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-010 ใบสมัครงานพนักงานรายวัน.xls" title="Home" download>F-PN-010 ใบสมัครงานพนักงานรายวัน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-015 ใบเตือน.pdf" title="Home" download>F-PN-015 ใบเตือน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-021 Rev.02  ใบขอเบิก.pdf" title="Home" download>F-PN-021 Rev.02 ใบขอเบิก</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-035 ตารางสรุปค่าแรงและค่าล่วงเวลา.xls" title="Home" download>F-PN-035 ตารางสรุปค่าแรงและค่าล่วงเวลา</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-041 สัญญาจ้างรายเดือน.docx" title="Home" download>F-PN-041 สัญญาจ้างรายเดือน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PN-042สัญญาจ้างรายวัน.docx" title="Home" download>F-PN-042สัญญาจ้างรายวัน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PU-002 Rev.05 ใบเเจ้งขอซื้อ - Copy.pdf" title="Home" download>F-PU-002 Rev.05 ใบเเจ้งขอซื้อ - Copy</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-PU-002 Rev.05 ใบเเจ้งขอซื้อ.pdf" title="Home" download>F-PU-002 Rev.05 ใบเเจ้งขอซื้อ</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-TN-003 Rev.04 แบบฟอร์มการเข้าฝึกอบรม.xls" title="Home" download>F-TN-003 Rev.04 แบบฟอร์มการเข้าฝึกอบรม</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-TN-003 Rev.05 การเพิ่มศักยภาพก่อนเข้าทำงาน.xls" title="Home" download>F-TN-003 Rev.05 การเพิ่มศักยภาพก่อนเข้าทำงาน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-TN-003 Rev.05 แบบฟอร์มการเข้าฝึกอบรม.xls" title="Home" download>F-TN-003 Rev.05 แบบฟอร์มการเข้าฝึกอบรม</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/F-TN-010 Rev.01 OJT ณ.จุดปฏิบัติงาน.xls" title="Home" download>F-TN-010 Rev.01 OJT ณ.จุดปฏิบัติงาน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/MEMO ขอลาออกจากกองทุนสำรองเลี้ยงชีพ.xls" title="Home" download>MEMO ขอลาออกจากกองทุนสำรองเลี้ยงชีพ</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/ขออนุมัติรับพนักงานใหม่.pdf" title="Home" download>ขออนุมัติรับพนักงานใหม่</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/บันทึกเข้าออกไลน์ผลิต.xls" title="Home" download>บันทึกเข้าออกไลน์ผลิต</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2 Rev.(1) (3).xls" title="Home" download>แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2 Rev.(1) (3)</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2 Rev..xls" title="Home" download>แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2 Rev.</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2.xls" title="Home" download>แบบขออนุญาตออกนอกและสิ่งของ รง. U1-U2</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบฟอร์มการตรวจสุขลักษณะส่วนบุคคลก่อนเข้าไลน์การผลิต.pdf" title="Home" download>แบบฟอร์มการตรวจสุขลักษณะส่วนบุคคลก่อนเข้าไลน์การผลิต</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบฟอร์มพนักงานหญิงตั้งครรภ์.xls" title="Home" download>แบบฟอร์มพนักงานหญิงตั้งครรภ์</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบฟอร์มล่วงเวลา.pdf" title="Home" download>แบบฟอร์มล่วงเวลา</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/แบบเสนอปรับสถานภาพพนักงาน.pdf" title="Home" download>แบบเสนอปรับสถานภาพพนักงาน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/ฟอร์มบันทึกความเสียหายและรับผิดชอบ.docx" title="Home" download>ฟอร์มบันทึกความเสียหายและรับผิดชอบ</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/ฟอร์มบันทึกภายใน.docx" title="Home" download>ฟอร์มบันทึกภายใน</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/ฟอร์มใบเบิกค่าใช้จ่าย  Rev.02.pdf" title="Home" download>ฟอร์มใบเบิกค่าใช้จ่าย Rev.02</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/สัญญาจ้าง URC รายเดือน updet 180662.docx" title="Home" download>สัญญาจ้าง URC รายเดือน updet 180662</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/สัญญาจ้าง URC รายวัน updet 180662.docx" title="Home" download>สัญญาจ้าง URC รายวัน updet 180662</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/หนังสือรับรองการแทนรูดบัตร.pdf" title="Home" download>หนังสือรับรองการแทนรูดบัตร</a>
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/HR/หนังสือสัญญาใช้หนี้ กยศ..docx" title="Home" download>หนังสือสัญญาใช้หนี้ กยศ.</a>
                                  </div>
                                </div>

                              </div>

                            </div>
                          </div>
                        </li>
                        <li class="nav-item menu-item ">
                          <div>
                            <div class="nav-link dropdown" style="float:left;">
                              <a class="btn" style="text-align: left;">แผนก IT</a>
                              <div class="dropdown-content" style="left:0;">
                                <div class="drop-wrapper">
                                  <a class="btn" style="font-size: smaller; text-align: left;" data-target="#dropright7" onclick="toggleDropdown(event)">เอกสารแบบฟอร์ม IT <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a>
                                  <div class="drop-menu dropright7 " id="dropright7">
                                    <a class="nav-link " style="font-size: smaller;" href="./wp-content/Form/IT/เอกสารแบบฟอร์ม IT.pdf" title="Home">เอกสารแบบฟอร์ม IT</a>
                                  </div>
                                </div>

                              </div>

                            </div>
                          </div>
                        </li>
                        <!-- <li class="nav-item menu-item ">
                          <div>
                            <div class="nav-link dropdown" style="float:left;">
                              <button class="dropbtn" style=" box-shadow: none;">แผนกจัดซื้อ</button>
                              <div class="dropdown-content" style="left:0;">
                                <a class="nav-link " href="http://192.0.2.180/reports/report/%E0%B8%84%E0%B8%A5%E0%B8%B1%E0%B8%87%E0%B8%AA%E0%B8%B4%E0%B8%99%E0%B8%84%E0%B9%89%E0%B8%B2%20URC3/Untitled" title="Home">ใบสั่งซื้อ</a>
                                <a class="nav-link " href="http://192.0.2.182:81/intranet/" title="Home">ใบเสนอราคา</a>
                                <a class="nav-link " href="http://192.0.2.182:81/intranet/" title="Home">ใบเคลม</a>
                              </div>
                            </div>
                          </div>
                        </li> -->
                        <li class="nav-item menu-item ">



                        </li>
                      </ul>
                    </div>
                  </div>

                  <!-- Right nav -->
                  <div class="d-none d-lg-block pl-3 ml-auto my-2 my-lg-0 position-relative align-items-center">
                    <?php if ($search_enable == true) { ?>
                      <!-- Search -->
                      <div class="dropdown show mg-search-box pr-2">
                        <a class="dropdown-toggle msearch ml-auto" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                          <i class="fa fa-search"></i>
                        </a>
                        <div class="dropdown-menu searchinner" aria-labelledby="dropdownMenuLink">
                          <?php get_search_form(); ?>
                        </div>
                      </div>
                  </div>
                  <!-- /Search -->
                <?php }
                    if ($subsc_enable == true) { ?>
                  <!-- Subscribe Button -->
                  <a href="<?php echo esc_url($subsc_link); ?>" <?php if ($subsc_target) { ?> target="_blank" <?php } ?> class="btn-bell btn-theme d-none d-lg-block mx-2"><i class="fa fa-bell"></i></a>
                  <!-- /Subscribe Button -->
                <?php } ?>
                <!-- /Right nav -->
                </div>
              </nav> <!-- /Navigation -->
            </div>
      </header>




      <div class="clearfix"></div>
      <?php if (is_front_page() || is_home()) { ?>
        <?php $show_popular_tags_title = newsup_get_option('show_popular_tags_title');
        $select_popular_tags_mode = newsup_get_option('select_popular_tags_mode');
        $number_of_popular_tags = newsup_get_option('number_of_popular_tags');
        newsup_list_popular_taxonomies($select_popular_tags_mode, $show_popular_tags_title, $number_of_popular_tags); ?>
      <?php } ?>
      <?php do_action('newsup_action_banner_exclusive_posts');
      do_action('newspaperex_action_front_page_main_section_1'); ?>
      <!-- Default dropright button -->


      <!-- <script src="./wp-content/themes/newspaperex/js/jquery-3.4.1.slim.min.js"></script> -->
      <script src="./wp-content/themes/newspaperex/js/jquery-3.6.0.min.js"></script>
      <script src="./wp-content/themes/newspaperex/js/jquery.marquee.min.js"></script>
      <script src="./wp-content/themes/newspaperex/js/popper.min.js"></script>
      <script src="./wp-content/themes/newspaperex/js/bootstrap.js"></script>
      <script src="./wp-content/themes/newspaperex/js/script.js"></script>
      <script src="./wp-content/themes/newspaperex/js/custom-elements.min.js"></script>
      <script src="./wp-content/themes/newspaperex/js/owl.carousel.js"></script>


      <script>
        function toggleDropdown(event) {
          event.preventDefault();
          const targetId = event.currentTarget.getAttribute('data-target');
          const dropdownMenu = document.querySelector(targetId);

          // Toggle display property
          if (dropdownMenu.style.display === 'block') {
            dropdownMenu.style.display = 'none';
          } else {
            dropdownMenu.style.display = 'block';
          }
        }

        // Close dropdowns if clicked outside
        document.addEventListener('click', function(event) {
          const isClickInside = event.target.closest('.drop-wrapper');

          if (!isClickInside) {
            const dropdowns = document.querySelectorAll('.drop-menu');
            dropdowns.forEach(function(dropdown) {
              dropdown.style.display = 'none';
            });
          }
        });
      </script>

</body>