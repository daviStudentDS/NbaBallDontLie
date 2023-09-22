using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using BallDo.Data;
using BallDo.Models;
using Microsoft.EntityFrameworkCore;

namespace BallDo.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class PlayerController : ControllerBase
    {
        private readonly ApplicationDbContext _context;

        public PlayerController(ApplicationDbContext context)
        {
            _context = context;
        }

        [HttpGet("{id}")]
        public IActionResult GetPlayer(int id)
        {
            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }

            var playerDTO = new PlayerDTO
            {
                Id = player.Id,
                Name = player.Name,
                Position = player.Position,
                Age = player.Age,
                GoalsScored = player.GoalsScored,
                TeamId = player.TeamId
            };

            return Ok(playerDTO);
        }

        [HttpPost]
        public IActionResult CreatePlayer(PlayerDTO playerDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var player = new Player
            {
                Name = playerDTO.Name,
                Position = playerDTO.Position,
                Age = playerDTO.Age,
                GoalsScored = playerDTO.GoalsScored,
                TeamId = playerDTO.TeamId ?? 0
            };

            _context.Players.Add(player);
            _context.SaveChanges();

            return CreatedAtAction(nameof(GetPlayer), new { id = player.Id }, player);
        }

        [HttpPut("{id}")]
        public IActionResult UpdatePlayer(int id, PlayerDTO playerDTO)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }

            player.Name = playerDTO.Name;
            player.Position = playerDTO.Position;
            player.Age = playerDTO.Age;
            player.GoalsScored = playerDTO.GoalsScored;
            player.TeamId = playerDTO.TeamId ?? 0;

            _context.SaveChanges();

            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult DeletePlayer(int id)
        {
            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }

            _context.Players.Remove(player);
            _context.SaveChanges();

            return NoContent();
        }
    }
}