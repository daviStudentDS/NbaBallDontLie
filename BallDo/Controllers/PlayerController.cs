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

        [HttpGet]
        public IActionResult GetAllPlayers()
        {
            var players = _context.Players
                .Select(p => new PlayerDTO
                {
                    Id = p.Id,
                    Name = p.Name,
                    Position = p.Position,
                    Age = p.Age,
                    GoalsScored = p.GoalsScored,
                    Team = new TeamDTO
                    {
                        Id = p.Team.Id,
                        Name = p.Team.Name
                    }
                })
                .ToList();

            return Ok(players);
        }

        [HttpGet("{id}")]
        public IActionResult GetPlayerById(int id)
        {
            var player = _context.Players
                .Where(p => p.Id == id)
                .Select(p => new PlayerDTO
                {
                    Id = p.Id,
                    Name = p.Name,
                    Position = p.Position,
                    Age = p.Age,
                    GoalsScored = p.GoalsScored,
                   
                })
                .FirstOrDefault();

            if (player == null)
            {
                return NotFound();
            }

            return Ok(player);
        }

        [HttpPost]
        public IActionResult CreatePlayer(Player player)
        {
            _context.Players.Add(player);
            _context.SaveChanges();
            return CreatedAtAction(nameof(GetPlayerById), new { id = player.Id }, player);
        }

        [HttpPut("{id}")]
        public IActionResult UpdatePlayer(int id, Player updatedPlayer)
        {
            var player = _context.Players.FirstOrDefault(p => p.Id == id);
            if (player == null)
            {
                return NotFound();
            }
            player.Name = updatedPlayer.Name;
            player.Position = updatedPlayer.Position;
            player.Age = updatedPlayer.Age;
            player.GoalsScored = updatedPlayer.GoalsScored;
            // Atualize outras propriedades conforme necessário
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
