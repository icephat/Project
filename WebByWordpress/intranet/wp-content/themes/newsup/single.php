<?php get_header(); ?>
<!-- =========================
     Page Content Section      
============================== -->
<main id="content" class="single-class content">
    <!--container-->
    <div class="container-fluid">
      <!--row-->
      <div class="row">
        <!--col-md-->
        <?php 
                    $newsup_single_page_layout = get_theme_mod('newsup_single_page_layout','single-align-content-right');
                    if($newsup_single_page_layout == "single-align-content-left")
                    { ?>
                    <aside class="col-lg-3 col-md-4">
                        <?php get_sidebar();?>
                    </aside>
                    <?php } ?>
                    <?php if($newsup_single_page_layout == "single-align-content-right"){
                    ?>
                    <div class="col-lg-9 col-md-8">
                    <?php } elseif($newsup_single_page_layout == "single-align-content-left") { ?>
                    <div class="col-lg-9 col-md-8">
                    <?php } elseif($newsup_single_page_layout == "single-full-width-content") { ?>
                     <div class="col-md-12">
                     <?php } ?>
		      <?php if(have_posts())
		        {
		      while(have_posts()) { the_post(); ?>
            <div class="mg-blog-post-box"> 
              <div class="mg-header">
                <?php $tags = get_the_tags();
                $newsup_single_post_category = esc_attr(get_theme_mod('newsup_single_post_category','true'));
                  if($newsup_single_post_category == true){ ?>
                <div class="mg-blog-category"> 
                      <?php newsup_post_categories(); ?>
                </div>
                <?php } ?>
                <h1 class="title single"> <a title="<?php the_title_attribute( array('before' => esc_html_e('Permalink to: ','newsup'),'after'  => '') ); ?>">
                  <?php the_title(); ?></a>
                </h1>
                <?php 
                $newsup_single_post_admin_details = esc_attr(get_theme_mod('newsup_single_post_admin_details','true'));
                $newsup_single_post_date = esc_attr(get_theme_mod('newsup_single_post_date','true'));
                $newsup_single_post_tag = esc_attr(get_theme_mod('newsup_single_post_tag','true'));
                $tag_list = get_the_tag_list();
                if(($newsup_single_post_admin_details == true) || ($newsup_single_post_date == true) || (($newsup_single_post_tag == true ) &&($tag_list) ) ) { ?>
                <div class="media mg-info-author-block"> 
                  <?php
                  if($newsup_single_post_admin_details == true){ ?>
                  <a class="mg-author-pic" href="<?php echo esc_url(get_author_posts_url( get_the_author_meta( 'ID' ) ));?>"> <?php echo get_avatar( get_the_author_meta( 'ID') , 150); ?> </a>
                  <?php } ?>
                  <div class="media-body">
                    <?php 
                  if($newsup_single_post_admin_details == true){ ?>
                    <h4 class="media-heading"><span><?php esc_html_e('By','newsup'); ?></span><a href="<?php echo esc_url(get_author_posts_url( get_the_author_meta( 'ID' ) ));?>"><?php the_author(); ?></a></h4>
                    <?php } 
                    if($newsup_single_post_date == true){ ?>
                    <span class="mg-blog-date"><i class="fas fa-clock"></i> 
                      <?php echo esc_html(get_the_date('M')); ?> <?php echo esc_html(get_the_date('j,')); ?> <?php echo esc_html(get_the_date('Y')); ?></span>
                    <?php }
                     if($newsup_single_post_tag == true){
                    $tag_list = get_the_tag_list();
                    if($tag_list){ ?>
                    <span class="newsup-tags"><i class="fas fa-tag"></i>
                      <?php $keys = array_keys($tags);
                       foreach ($tags as $key => $tag) {
                          $tag_link = get_tag_link($tag->term_id);
                          if ($key === end($keys)) {
                            echo '<a href="'.esc_url($tag_link).'">#'.esc_html($tag->name).'</a>';
                          } else {
                            echo ' <a href="'.esc_url($tag_link).'">#'.esc_html($tag->name).'</a>, ';
                          }
                        }  ?>
                    </span>
                  <?php } } ?>
                  </div>
                </div>
              <?php }  ?>
              </div>
              <?php
              $single_show_featured_image = esc_attr(get_theme_mod('single_show_featured_image','true'));
              if($single_show_featured_image == true) {
              if(has_post_thumbnail()){
                the_post_thumbnail( '', array( 'class'=>'img-fluid' ) );
                
                  $thumbnail_id = get_post_thumbnail_id();
                  $caption = get_post($thumbnail_id)->post_excerpt;

                  if (!empty($caption)) {
                    echo '<span class="featured-image-caption">' . esc_html($caption) . '</span>';
                  }
                } }?>
              <article class="page-content-single small single">
                <?php the_content(); ?>
                <?php newsup_edit_link(); ?>
                <?php  newsup_social_share_post($post); ?>
                <div class="clearfix mb-3"></div>
                <?php
                $prev =  (is_rtl()) ? "left" : "right";
                $next =  (is_rtl()) ? "right" : "left";
            the_post_navigation(array(
                'prev_text' => '%title <div class="fa fa-angle-double-'.$prev.'"></div><span></span>',
                'next_text' => '<div class="fa fa-angle-double-'.$next.'"></div><span></span> %title',
                'in_same_term' => true,
            ));
            ?>
            <?php wp_link_pages(array(
                'before' => '<div class="single-nav-links">',
                'after' => '</div>',
            ));
            ?>
              </article>
            </div>
		      <?php } $newsup_enable_single_post_admin_details = esc_attr(get_theme_mod('newsup_enable_single_post_admin_details',true));
            if($newsup_enable_single_post_admin_details == true) { ?>
           <div class="media mg-info-author-block">
            <a class="mg-author-pic" href="<?php echo esc_url(get_author_posts_url( get_the_author_meta( 'ID' ) ));?>"><?php echo get_avatar( get_the_author_meta( 'ID') , 150); ?></a>
                <div class="media-body">
                  <h4 class="media-heading"><?php esc_html_e('By','newsup'); ?> <a href ="<?php echo esc_url(get_author_posts_url( get_the_author_meta( 'ID' ) ));?>"><?php the_author(); ?></a></h4>
                  <p><?php the_author_meta( 'description' ); ?></p>
                </div>
            </div>
            <?php } $newsup_enable_related_post = esc_attr(get_theme_mod('newsup_enable_related_post','true'));
                    if($newsup_enable_related_post == true){ ?>
              <div class="mg-featured-slider p-3 mb-4">
                        <!--Start mg-realated-slider -->
                        <div class="mg-sec-title">
                            <!-- mg-sec-title -->
                            <?php $newsup_related_post_title = get_theme_mod('newsup_related_post_title', esc_html__('Related Post','newsup'))?>
                            <h4><?php echo esc_html($newsup_related_post_title);?></h4>
                        </div>
                        <!-- // mg-sec-title -->
                           <div class="row">
                                <!-- featured_post -->
                                  <?php
                                  global $post;
                                  $categories = get_the_category($post->ID);
                                  $number_of_related_posts = 3;

                                  if ($categories) {
                                  $cat_ids = array();
                                  foreach ($categories as $category) $cat_ids[] = $category->term_id;
                                  $args = array(
                                  'category__in' => $cat_ids,
                                  'post__not_in' => array($post->ID),
                                  'posts_per_page' => $number_of_related_posts, // Number of related posts to display.
                                  'ignore_sticky_posts' => 1
                                   );
                                  $related_posts = new wp_query($args);

                                  while ($related_posts->have_posts()) {
                                  $related_posts->the_post();
                                  global $post;
                                  $url = newsup_get_freatured_image_url($post->ID, 'newsup-featured'); 
                                  ?>
                                    <!-- blog -->
                                  <div class="col-md-4">
                                    <div class="mg-blog-post-3 minh back-img mb-md-0 mb-2" 
                                    <?php if(has_post_thumbnail()) { ?>
                                    style="background-image: url('<?php echo esc_url($url); ?>');" <?php } ?>>
                                      <div class="mg-blog-inner">
                                          <?php $newsup_enable_single_post_category = esc_attr(get_theme_mod('newsup_enable_single_post_category','true'));

                                            if($newsup_enable_single_post_category == true){ ?>
                                            <div class="mg-blog-category"> <?php newsup_post_categories(); ?>
                                          </div> <?php } ?>
                                            <h4 class="title"> <a href="<?php the_permalink(); ?>" title="<?php the_title_attribute( array('before' => 'Permalink to: ','after'  => '') ); ?>">
                                              <?php the_title(); ?></a>
                                             </h4>
                                            <div class="mg-blog-meta"> 
                                            <?php $newsup_enable_single_post_date = esc_attr(get_theme_mod('newsup_enable_single_post_date','true'));
                                                if($newsup_enable_single_post_date == true){
                                            ?>
                                              <span class="mg-blog-date">
                                                <i class="fas fa-clock"></i> 
                                              
                                              <?php echo esc_html(get_the_date('M')); ?> <?php echo esc_html(get_the_date('j,')); ?> <?php echo esc_html(get_the_date('Y')); ?>

                                               </span>
                                            <?php } $newsup_enable_single_post_admin = esc_attr(get_theme_mod('newsup_enable_single_post_admin','true'));
                                              if($newsup_enable_single_post_admin == true) { newsup_author_content(); } newsup_edit_link(); ?> </div>   
                                        </div>
                                    </div>
                                  </div>
                                    <!-- blog -->
                                    <?php }
                }
                wp_reset_postdata();
                ?>
                            </div>
                            
                    </div>
                    <!--End mg-realated-slider -->
                  <?php } } $newsup_enable_single_post_comments = esc_attr(get_theme_mod('newsup_enable_single_post_comments',true));
                  if($newsup_enable_single_post_comments == true) {
                  if (comments_open() || get_comments_number()) :
                  comments_template();
                  endif; } ?>
      </div>
       <?php if($newsup_single_page_layout == "single-align-content-right") { ?>
      <!--sidebar-->
          <!--col-md-3-->
            <aside class="col-lg-3 col-md-4">
                  <?php get_sidebar();?>
            </aside>
          <!--/col-md-3-->
      <!--/sidebar-->
      <?php } ?>
    </div>
  </div>
</main>
<?php get_footer(); ?>