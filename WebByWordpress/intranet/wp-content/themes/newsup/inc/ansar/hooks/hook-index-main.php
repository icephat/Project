<?php if (!function_exists('newsup_content_layouts')) :
    function newsup_content_layouts()
    { 
        $newsup_content_layout = esc_attr(get_theme_mod('newsup_content_layout','align-content-right'));

        if($newsup_content_layout == "align-content-right" || $newsup_content_layout == "align-content-left"){ ?>
            <div class="col-md-8">
                <?php get_template_part('content',''); ?>
            </div>
        <?php } elseif($newsup_content_layout == "full-width-content") { ?>
            <div class="col-md-12">
                <?php get_template_part('content',''); ?>
            </div>
        <?php }  if($newsup_content_layout == "grid-left-sidebar" || $newsup_content_layout == "grid-right-sidebar"){ ?>
            <div class="col-md-8">
                <?php get_template_part('content','grid'); ?>
            </div>
        <?php } elseif($newsup_content_layout == "grid-fullwidth") { ?>
            <div class="col-md-12">
                <?php get_template_part('content','grid'); ?>
            </div>
        <?php } 
    }
endif;
add_action('newsup_action_content_layouts', 'newsup_content_layouts', 40);


if (!function_exists('newsup_main_content_layouts')) :
    function newsup_main_content_layouts()
    { 
        $newsup_content_layout = esc_attr(get_theme_mod('newsup_content_layout','align-content-right'));

        if($newsup_content_layout == "align-content-left" || $newsup_content_layout == "grid-left-sidebar" ){ ?>
            <aside class="col-md-4">
                <?php get_sidebar();?>
            </aside>
        <?php } ?>
        <?php do_action('newsup_action_content_layouts'); ?>
        <?php if($newsup_content_layout == "align-content-right" || $newsup_content_layout == "grid-right-sidebar")  { ?>
            <aside class="col-md-4">
                <?php get_sidebar();?>
            </aside>
        <?php }
    }
endif;
add_action('newsup_action_main_content_layouts', 'newsup_main_content_layouts', 40);