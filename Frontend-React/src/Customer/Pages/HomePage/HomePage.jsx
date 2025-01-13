import React from 'react'
import MainCarousel from '../../componet/HomeCarousel/MainCarousel'
import HomeSectionCarousel from '../../componet/HomeSectionCarouselCart/HomeSectionCarousel'
import { mens_kurta } from '../../../Data/mens_kurta'

const HomePage = () => {
  return (
    <div>
      <MainCarousel />
      <div className='space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10'>
       <HomeSectionCarousel data={mens_kurta} secitionName={"Men's Kurta "}/>
       <HomeSectionCarousel data={mens_kurta} secitionName={"Men's Shoes "}/>
       <HomeSectionCarousel data={mens_kurta} secitionName={"Men's Shirt "}/>
       <HomeSectionCarousel data={mens_kurta} secitionName={"Women's Saree "}/>
       <HomeSectionCarousel data={mens_kurta} secitionName={"Women's Dress "}/>
       
      </div>
    </div>
  )
}

export default HomePage
