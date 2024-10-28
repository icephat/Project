<?php if (!function_exists('newspaperex_frontpage_editor_post_section')) :

    /**
     *
     * @since Newspaperex
     *
     *
     */
    function newspaperex_frontpage_editor_post_section()
    {

?>
        <style>
            .col-md-3 img:hover {
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            }

            @keyframes move {
                0% {
                    transform: translateY(0);
                }

                25% {
                    transform: translateY(-5px);
                }

                50% {
                    transform: translateY(5px);
                }

                100% {
                    transform: translateY(0);
                }
            }

            .col-md-2 img:hover {
                animation: move 0.5s;
            }
        </style>
        <div class="col-md-12" style="font-size:medium; ">
            <div class="row">
                <div class="col-md-9" style="font-size:medium;">
                    <div class="row text-center">
                        <!-- ============================================================= ALL ==================================================================== -->
                        <div class="col-md-2 center">
                            <a target="_blank" href="http://www.urcrice.com/LD"><img src="./wp-content/img/1.png"></a>
                            <p class="th">โหลด</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/QC"><img src="./wp-content/img/2.png"></a>
                            <p class="th">ตรวจสอบ</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="https://mail.universalrice.com/owa"><img src="./wp-content/img/3.png"></a>
                            <p class="th">เมล์นอก</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="https://192.0.2.221/owa"><img src="./wp-content/img/3.png"></a>
                            <p class="th">เมล์นอก (สำรอง)</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="https://192.0.2.210/owa"><img src="./wp-content/img/4.png"></a>
                            <p class="th">เมล์ใน</p>
                        </div>
                        <!--<div class="col-md-3 center"><a target="_blank" href="http://192.0.2.165/home/mail.php"><img src="IMG/4.png"></a><p class="th">เมล์ใน</p></div>-->
                        <!--<div class="col-md-3 center"><a target="_blank" href="https://admin-official.line.me/"><img src="IMG/line.png"></a><p class="th">@LINE</p></div>-->
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/it/TEL"><img src="./wp-content/img/8.png"></a>
                            <p class="th">ข้อมูลติดต่อ</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/REPORT"><img src="./wp-content/img/10.png"></a>
                            <p class="th">ข้อมูลการผลิต</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/QCCO/Login.php"><img src="./wp-content/img/9.png"></a>
                            <p class="th">ตรวจสิ่งเจือปน</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="http://www.urcrice.com/eService"><img src="./wp-content/img/5.png"></a>
                            <p class="th">e-Service</p>
                        </div>
                        <!--<div class="col-md-3 center"><a target="_blank" href="http://192.0.2.165/re/upload/"><img src="IMG/5.png"></a><p class="th">แจ้งปัญหา</p><br></div> -->
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/PS"><img src="./wp-content/img/7.png"></a>
                            <p class="th">เครื่องจักร</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/FORM" class="initialism standalone_open"><img src="./wp-content/img/16.png"></a>
                            <p class="th">ลงทะเบียน</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.180/reports"><img src="./wp-content/img/SSRS.png"></a>
                            <p class="th">Reporting Services</p>
                        </div>
                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/issue/upload/"><img src="./wp-content/img/19.png"></a>
                            <p class="th">ความปลอดภัยอาหาร</p>
                        </div>
                        <!-- ============================================================= IT & STORE ============================================================= -->

                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/tk"><img src="./wp-content/img/store.png"></a>
                            <p class="th">Store</p>
                        </div>

                        <!-- ============================================================= IT & FUMIGATION ============================================================= -->

                        <div class="col-md-2 center">
                            <a target="_blank" href="//192.0.2.165/fum/AOPView/"><img src="./wp-content/img/18.png"></a>
                            <p class="th">รมยากำจัดแมลง</p>
                        </div>

                        <!-- ============================================================= IT & RT & QC & ART ============================================================= -->

                        <div class="col-md-2 center"><a target="_blank" href="http://www.urcrice.com/itb/rt/"><img src="./wp-content/img/20.png"></a>
                            <p class="th">เช็คแบบวัสดุบรรจุ</p>
                        </div>

                        <!-- ============================================================= TEMP URC3 ============================================================= -->
                        <div class="col-md-2 center"><a target="_blank" href="http://u3.urcrice.com/program/login"><img src="./wp-content/img/21.png"></a>
                            <p class="th">ตู้อบฟีนิกซ์ URC3</p>
                        </div>

                        <!-- ============================================================= Library ============================================================= -->
                        <div class="col-md-2 center"><a target="_blank" href="//192.0.2.165/km/"><img src="./wp-content/img/22.png"></a>
                            <p class="th">ห้องสมุด</p>
                        </div>
                        <!-- ============================================================= Sale ============================================================= -->

                        <div class="col-md-2 center"><a target="_blank" href="//192.0.2.165/bompk/"><img src="./wp-content/img/sale.png"></a>
                            <p class="th">Packaging Stocks</p>
                        </div>

                        <!-- ============================================================= PM Computer ============================================================= -->

                        <div class="col-md-2 center"><a target="_blank" href="http://www.urcrice.com/e-Service/pmcom.aspx"><img src="./wp-content/img/23.png"></a>
                            <p class="th">PM Computer</p>
                        </div>

                        <!-- ============================================================= PM Computer ============================================================= -->

                        <div class="col-md-2 center"><a target="_blank" href="http://www.urcrice.com/e-Service/DailyWork.aspx"><img src="./wp-content/img/24.png"></a>
                            <p class="th">Time-Sheet</p>
                        </div>

                        <div class="col-md-2 center"><a target="_blank" href="http://e-Book.urcrice.com/"><img src="./wp-content/img/25.jpg"></a>
                            <p class="th">e-Book</p>
                        </div>

                        <div class="col-md-2 center"><a target="_blank" href="http://app.urcrice.com:81/inventory/"><img src="./wp-content/img/26.jpg"></a>
                            <p class="th">Asset Management (IT)</p>
                        </div>

                        <div class="col-md-2 center"><a target="_blank" href="//192.0.2.251"><img src="./wp-content/img/filesharing.png"></a>
                            <p class="th">Share Drive</p>
                        </div>
                        <!-- ============================================================= END ALL ==================================================================== -->
                    </div>
                </div>
                <?php do_action('newspaperex_action_banner_tabbed_posts'); ?>

            </div>
        </div>
        <?php }


endif;

add_action('newspaperex_action_front_page_editor_section', 'newspaperex_frontpage_editor_post_section', 35);

//Front Page Banner
if (!function_exists('newspaperex_front_page_banner_section')) :
    /**
     *
     * @since Newspaperex
     *
     */
    function newspaperex_front_page_banner_section()
    {
        if (is_front_page() || is_home()) {
            $newsup_enable_main_slider = newsup_get_option('show_main_news_section');
            $select_vertical_slider_news_category = newsup_get_option('select_vertical_slider_news_category');
            $vertical_slider_number_of_slides = newsup_get_option('vertical_slider_number_of_slides');
            $all_posts_vertical = newsup_get_posts($vertical_slider_number_of_slides, $select_vertical_slider_news_category);
            if ($newsup_enable_main_slider) :

                $main_banner_section_background_image = newsup_get_option('main_banner_section_background_image');
                $main_banner_section_background_image_url = wp_get_attachment_image_src($main_banner_section_background_image, 'full');
                if (!empty($main_banner_section_background_image)) { ?>
                    <section class="mg-fea-area over" style="background-image:url('<?php echo esc_url($main_banner_section_background_image_url[0]); ?>');">
                    <?php } else { ?>
                        <section class="mg-fea-area">
                        <?php  } ?>
                        <div class="overlay">
                            <div class="container-fluid">
                                <div class="row">
                                    <?php do_action('newspaperex_action_front_page_editor_section'); ?>
                                    <div class="col-md-6 col-sm-6">
                                        <div id="homemain" class="homemain owl-carousel mr-bot60 pd-r-10">
                                            <!-- <?php newsup_get_block('list', 'banner'); ?> -->
                                        </div>
                                    </div>
                                    <!-- <?php do_action('newspaperex_action_banner_tabbed_posts'); ?> -->
                                </div>
                            </div>
                        </div>
                        </section>
                        <!--==/ Home Slider ==-->
                    <?php endif; ?>
                    <!-- end slider-section -->
                <?php }
        }
    endif;
    add_action('newspaperex_action_front_page_main_section_1', 'newspaperex_front_page_banner_section', 40);



    //Banner Tabed Section
    if (!function_exists('newspaperex_banner_tabbed_posts')) :
        /**
         *
         * @since newspaperex 1.0.0
         *
         */
        function newspaperex_banner_tabbed_posts()
        {

            $show_excerpt = 'false';
            $excerpt_length = '20';
            $number_of_posts = '4';

            $enable_categorised_tab = 'true';
            $latest_title = newsup_get_option('latest_tab_title');
            $popular_title = newsup_get_option('popular_tab_title');
            $categorised_title = newsup_get_option('trending_tab_title');
            $category = newsup_get_option('select_trending_tab_news_category');
            $tab_id = 'tan-main-banner-latest-trending-popular'
                ?>
                <div class="col-md-3 col-sm-6 top-right-area">
                    <div id="exTab2">
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#<?php echo esc_attr($tab_id); ?>-recent" aria-controls="<?php esc_attr_e('Recent', 'newspaperex'); ?>">
                                    <i class="fa fa-clock-o"></i><?php echo esc_html($latest_title); ?>
                                </a>
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#<?php echo esc_attr($tab_id); ?>-popular" aria-controls="<?php esc_attr_e('Popular', 'newspaperex'); ?>">
                                    <i class="fa fa-fire"></i> <?php echo esc_html($popular_title); ?>
                                </a>
                            </li>


                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#<?php echo esc_attr($tab_id); ?>-categorised" aria-controls="<?php esc_attr_e('Categorised', 'newspaperex'); ?>">
                                    <i class="fa fa-bolt"></i> <?php echo esc_html($categorised_title); ?>
                                </a>
                            </li>

                        </ul>
                        <div class="tab-content">
                            <div id="<?php echo esc_attr($tab_id); ?>-recent" role="tabpanel" class="tab-pane active">
                                <?php
                                newsup_render_posts('recent', $show_excerpt, $excerpt_length, $number_of_posts);
                                ?>
                            </div>


                            <div id="<?php echo esc_attr($tab_id); ?>-popular" role="tabpanel" class="tab-pane">
                                <?php
                                newsup_render_posts('popular', $show_excerpt, $excerpt_length, $number_of_posts);
                                ?>
                            </div>

                            <?php if ($enable_categorised_tab == 'true') : ?>
                                <div id="<?php echo esc_attr($tab_id); ?>-categorised" role="tabpanel" class="tab-pane ">
                                    <?php
                                    newsup_render_posts('categorised', $show_excerpt, $excerpt_length, $number_of_posts, $category);
                                    ?>
                                </div>
                            <?php endif; ?>

                        </div>
                    </div>
                    <?php

                }
            endif;

            add_action('newspaperex_action_banner_tabbed_posts', 'newspaperex_banner_tabbed_posts', 10);



            //Banner Advertisment
            if (!function_exists('newspaperex_right_banner_advertisement')) :
                /**
                 *
                 * @since Newspaperex 0.1
                 *
                 */
                function newspaperex_right_banner_advertisement()
                {

                    if (('' != newsup_get_option('banner_right_advertisement_section'))) {
                        $newsup_center_logo_title = get_theme_mod('newsup_center_logo_title', false);
                        if ($newsup_center_logo_title == false) {

                            if (newsup_get_option('banner_advertisement_section')) {
                    ?>
                                <div class="col-md-4 col-sm-8">
                                <?php } else { ?>
                                    <div class="col-md-8 col-sm-8">
                                    <?php }
                            } else { ?>
                                    <div class="col-8 text-center mx-auto">
                                    <?php }
                                if (('' != newsup_get_option('banner_right_advertisement_section'))) :

                                    $newsup_right_banner_advertisement = newsup_get_option('banner_right_advertisement_section');
                                    $newsup_right_banner_advertisement = absint($newsup_right_banner_advertisement);
                                    $newsup_right_banner_advertisement = wp_get_attachment_image($newsup_right_banner_advertisement, 'full');
                                    $banner_right_advertisement_section_url = newsup_get_option('banner_advertisement_section_url');
                                    $banner_right_advertisement_section_url = isset($banner_right_advertisement_section_url) ? esc_url($banner_right_advertisement_section_url) : '#';
                                    $newsup_right_open_on_new_tab = get_theme_mod('newsup_right_open_on_new_tab', true);
                                    ?>
                                        <div class="header-ads">
                                            <a class="pull-right" <?php echo esc_url($banner_right_advertisement_section_url); ?> href="<?php echo $banner_right_advertisement_section_url; ?>" <?php if ($newsup_right_open_on_new_tab) { ?>target="_blank" <?php } ?>>
                                                <?php echo $newsup_right_banner_advertisement; ?>
                                            </a>
                                        </div>
                                    <?php endif; ?>

                                    </div>
                                    <!-- Trending line END -->
                                    <?php
                                }
                            }
                        endif;

                        add_action('newspaperex_action_right_banner_advertisement', 'newspaperex_right_banner_advertisement', 10);




                        //Banner Advertisment
                        if (!function_exists('newspaperex_left_banner_advertisement')) :
                            /**
                             *
                             * @since Newsup 1.0.0
                             *
                             */
                            function newspaperex_left_banner_advertisement()
                            {

                                if (('' != newsup_get_option('banner_advertisement_section'))) {
                                    $newsup_center_logo_title = get_theme_mod('newsup_center_logo_title', false);
                                    if ($newsup_center_logo_title == false) {

                                        if (newsup_get_option('banner_right_advertisement_section')) {
                                    ?>
                                            <div class="col-md-4 col-sm-8">
                                            <?php } else { ?>
                                                <div class="col-md-8 col-sm-8">
                                                <?php }
                                        } else { ?>
                                                <div class="col-8 text-center mx-auto">
                                                <?php }
                                            if (('' != newsup_get_option('banner_advertisement_section'))) :

                                                $newsup_banner_advertisement = newsup_get_option('banner_advertisement_section');
                                                $newsup_banner_advertisement = absint($newsup_banner_advertisement);
                                                $newsup_banner_advertisement = wp_get_attachment_image($newsup_banner_advertisement, 'full');
                                                $newsup_banner_advertisement_url = newsup_get_option('banner_left_advertisement_section_url');
                                                $newsup_banner_advertisement_url = isset($newsup_banner_advertisement_url) ? esc_url($newsup_banner_advertisement_url) : '#';
                                                $newsup_open_on_new_tab = get_theme_mod('newsup_open_on_new_tab', true);
                                                ?>
                                                    <div class="header-ads">
                                                        <a class="pull-right" <?php echo esc_url($newsup_banner_advertisement_url); ?> href="<?php echo $newsup_banner_advertisement_url; ?>" <?php if ($newsup_open_on_new_tab) { ?>target="_blank" <?php } ?>>
                                                            <?php echo $newsup_banner_advertisement; ?>
                                                        </a>
                                                    </div>
                                                <?php endif; ?>

                                                </div>
                                                <!-- Trending line END -->
                                    <?php
                                }
                            }
                        endif;

                        add_action('newspaperex_action_banner_advertisement', 'newspaperex_left_banner_advertisement', 10);
